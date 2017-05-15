package monads

import cats.data.State

import scala.collection.immutable.Nil

object PostCalc {

  type CalcState[A] = State[List[Int], A]

  def evalAll(input: List[String]): CalcState[Int] = {
    val states: Seq[CalcState[Int]] = input map evalOne
    states.foldLeft(State.pure[List[Int], Int](0): CalcState[Int])( (state: CalcState[Int], state2: CalcState[Int] ) => state.flatMap( _ => state2))
  }

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => State[List[Int], Int] {
      list => {
        val h1 :: h2 :: tail = list
        (h1 + h2 :: tail, h1 + h2)
      }
    }
    case "*" => State[List[Int], Int] {
      list => {
        val h1 :: h2 :: tail = list
        (h1 * h2 :: tail, h1 * h2)
      }
    }
    case s => State[List[Int], Int] {
      list => {
        val num = s.toInt
        (num :: list, num)
      }
    }
  }

}

object Main extends App {

  import PostCalc._

  val program = for {
    _ <- evalOne("1")
    _ <- evalOne("2")
    ans <- evalOne("+")
  } yield ans
  println(program.runA(Nil).value)

  val a = evalOne("42").runA(Nil).value
  println(a)

  println(evalAll(List("1", "2", "+", "3", "*")).runA(Nil).value)

  val program2 = for {
    _ <- evalAll(List("1", "2", "+"))
    _ <- evalAll(List("3", "4", "+"))
    ans <- evalOne("*")
  } yield ans

  println(program2.runA(Nil).value)
}

