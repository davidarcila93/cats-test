package chapter4.section1

import scala.language.higherKinds

object Main extends App {
  def flatMap[F[_], A, B](value: F[A])(func: A => F[B]): F[B] = ???
  def pure[F[_], A](value: A): F[A] = ???
  def map[F[_], A, B](value: F[A])(func: A => B): F[B] = flatMap(value)( func andThen pure )
}
