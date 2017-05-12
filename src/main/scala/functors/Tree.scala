package functors

import cats.Functor

/**
  * Created by david on 12/05/17.
  */
sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def leaf[A](value: A): Tree[A] = Leaf(value)

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A, B](fa: Tree[A])(f: (A) => B): Tree[B] = fa match {
      case Leaf(a) => Leaf(f(a))
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
    }
  }
}
