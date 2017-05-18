package monads

import cats.data.State

object PostCalc {

  type CalcState[A] = State[List[Int], A]

  def evalAll(input: List[String]): CalcState[Int] = {
    val states: Seq[CalcState[Int]] = input map evalOne
    states.foldLeft(State.pure[List[Int], Int](0): CalcState[Int])((state: CalcState[Int], state2: CalcState[Int]) => state.flatMap(_ => state2))
  }

  def evalOne(sym: String): CalcState[Int] = {

    def stateOp(f: (Int, Int) => Int) = State[List[Int], Int] {
      list => {
        val h1 :: h2 :: tail = list
        (f(h1, h2) :: tail, f(h1, h2))
      }
    }

    sym match {
      case "+" => stateOp(_ + _)
      case "*" => stateOp(_ * _)
      case "/" => stateOp(_ / _)
      case "-" => stateOp(_ - _)
      case s => State[List[Int], Int] {
        list => {
          val num = s.toInt
          (num :: list, num)
        }
      }
    }
  }

}
