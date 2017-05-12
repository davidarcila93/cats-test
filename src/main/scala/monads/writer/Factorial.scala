package monads.writer

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.syntax.writer._

import scala.collection.immutable.Seq

object Factorial {

  def slowly[A](body: => A): A =
    try body finally Thread.sleep(100)

  type Logged[A] = Writer[Vector[String], A]

  def factorial(n: Int): Writer[Vector[String], Int] = {
    slowly(if (n == 0) 1.pure[Logged] else for {
      cur <- n.pure[Logged]
      rec <- factorial(n-1)
      _ <- Vector(s"fact $n ${cur*rec}").tell
    } yield cur * rec )
  }

}

object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val result: Seq[Writer[Vector[String], Int]] = Await.result(Future.sequence(Vector(
    Future(Factorial.factorial(5)),
    Future(Factorial.factorial(5))
  )), Duration.Inf)

  print(result)

}