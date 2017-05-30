package foldable

/**
  * Created by david on 30/05/17.
  */
object ListOps {

  def map[A, B](list: List[A], f: A => B): List[B] =
    list.foldRight(List[B]())((item, acc) => f(item) :: acc)

  def flatMap[A, B](list: List[A], f: A => List[B]): List[B] =
    list.foldRight(List[B]())((item, acc) => f(item) ++ acc)

  def filter[A](list: List[A], f: A => Boolean): List[A] =
    list.foldRight(List[A]())((item, acc) => if (f(item)) item :: acc else acc)

}
