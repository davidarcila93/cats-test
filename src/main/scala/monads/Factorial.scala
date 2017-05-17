package monads

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

object Factorial {

  private[this] def slowly[A](body: => A): A =
    try body finally Thread.sleep(100)

  type Logged[A] = Writer[Vector[String], A]

  def factorial(n: Int): Writer[Vector[String], Int] = {
    slowly(if (n == 0) 1.pure[Logged] else for {
      cur <- n.pure[Logged]
      rec <- factorial(n - 1)
      _ <- Vector(s"fact $n ${cur * rec}").tell
    } yield cur * rec)
  }

}