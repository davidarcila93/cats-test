package monads

import cats.Eval

/**
  * Created by david on 17/05/17.
  */
object FoldRight {
  def naiveFoldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, naiveFoldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  def safeFoldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer( safeFoldRight(tail, acc)(fn).map( a => fn(head, a) ) )
      case Nil =>
        Eval.now(acc)
    }
}
