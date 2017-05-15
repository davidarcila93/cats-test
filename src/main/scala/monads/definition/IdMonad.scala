package monads.definition

/**
  * Created by david on 15/05/17.
  */
object IdMonad {

  type Id[A] = A

  implicit def idMonad: Monad[Id] = new Monad[Id] {
    override def pure[A](a: A): Id[A] = a

    override def flatMap[A, B](value: Id[A])(func: (A) => Id[B]): Id[B] = func(value)
  }

}


