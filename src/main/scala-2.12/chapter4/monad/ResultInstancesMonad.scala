package chapter4.monad

import cats.Monad
import chapter3.{Failure, Result, Success, Warning}

import scala.annotation.tailrec

object ResultInstancesMonad {

  implicit val resultMonad: Monad[Result] = new Monad[Result] {

    def flatMap[A, B](fa: Result[A])(f: (A) => Result[B]): Result[B] = fa match {
      case Success(a) => f(a)
      case Failure(s) => Failure(s)
      case Warning(a, s) => f(a) match {
        case Success(aa) => Warning(aa, s)
        case Failure(ss) => Failure(s"$s $ss")
        case Warning(aa, ss) => Warning(aa, s"$s $ss")
      }
    }

    def pure[A](x: A): Result[A] = Success(x)

    def tailRecM[A, B](a: A)(f: (A) => Result[Either[A, B]]): Result[B] = {
      @tailrec
      def rec(a: A)(s: String)(f: (A) => Result[Either[A, B]]): Result[B] = {
        f(a) match {
          case Success(Left(aa)) => tailRecM(aa)(f)
          case Success(Right(b)) => Success(b)
          case Failure(message) => Failure(s"$s $message")
          case Warning(Right(b), message) => Warning(b, message)
          case Warning(Left(aa), message) => rec( aa )(s"$s $message")(f)
        }
      }
      rec(a)("")(f)
    }
  }

}

object Main extends App {

  import ResultInstancesMonad._
  import cats.syntax.functor._
  import cats.syntax.flatMap._
  import cats.syntax.applicative._

  val success: Result[Int] = Success(10)
  println( success map { _ + 2 }  )
  println( success flatMap { _ => Warning(1, "Paila") }  )
  println()

  val warning: Result[Int] = Warning(10, "WARNING")
  println( warning map { _ + 2 }  )
  println( warning flatMap  { _ => true.pure }  )
  println( warning flatMap { _ => Warning(1, "Paila") }  )
  println( warning flatMap { _ => Failure(":/") }  )
  println()

  val failure: Result[Int] = Failure("FAIL")
  println( failure map { _ + 2 }  )
  println( failure flatMap  { _ => "2".pure }  )
  println()

  val sum = for {
    a <- success
    b <- warning
  } yield a+b
  println(sum)

  val sum2 = for {
    a <- success
    b <- failure
  } yield a+b
  println(sum2)

  val sum3 = for {
    a <- warning
    b <- success
  } yield a*b
  println(sum3)
}