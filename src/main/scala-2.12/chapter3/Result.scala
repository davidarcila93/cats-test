package chapter3

import cats.Functor

sealed trait Result[+A]

final case class Success[A](value: A) extends Result[A]

final case class Warning[A](value: A, message: String) extends Result[A]

final case class Failure(message: String) extends Result[Nothing]

object ResultInstancesFunctor {
  implicit val resultFunctor: Functor[Result] = new Functor[Result] {
    def map[A, B](fa: Result[A])(f: (A) => B): Result[B] = fa match {
      case Success(a) => Success(f(a))
      case Warning(a, m) => Warning(f(a), m)
      case Failure(m) => Failure(m)
    }
  }
}

object MyApp extends App {

  import cats.syntax.functor._
  import ResultInstancesFunctor._

  val success: Result[Int] = Success(10)
  println( success map { _ + 2 }  )

  val warning: Result[Int] = Warning(10, "WARNING")
  println( warning map { _ + 2 }  )

  val failure: Result[Int] = Failure("FAIL")
  println( failure map { _ + 2 }  )

}