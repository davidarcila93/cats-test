package monads

import cats.Monad
import functors.{Branch, Leaf, Tree}

import scala.annotation.tailrec

/**
  * Created by david on 18/05/17.
  */
object TreeMonadInstance {
  implicit val treeMonad = new Monad[Tree] {

    override def flatMap[A, B](fa: Tree[A])(f: (A) => Tree[B]): Tree[B] = fa match {
      case Leaf(a) => f(a)
      case Branch(l, r) => Branch(flatMap(l)(f), flatMap(r)(f))
    }

    override def pure[A](a: A): Tree[A] = Leaf(a)

    override def tailRecM[A, B](a: A)(f: (A) => Tree[Either[A, B]]): Tree[B] = {

      def rec(t: Tree[Either[A, B]])(f: (A) => Tree[Either[A, B]]): Tree[B] = t match {
        case Leaf(Left(v)) => rec(f(v))(f)
        case Leaf(Right(v)) => Leaf(v)
        case Branch(l, r) => Branch(rec(l)(f), rec(r)(f))
      }

      rec(Leaf(Left(a)))(f)
    }

  }
}
