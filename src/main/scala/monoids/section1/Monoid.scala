package monoids.section1

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] =
    monoid
}

object MyApp extends App {

  val andMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def empty = true
    def combine(x: Boolean, y: Boolean) = x && y
  }

  val orMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def empty = false
    def combine(x: Boolean, y: Boolean) = x || y
  }

  println( andMonoid.combine(andMonoid.empty, true) )
  println( andMonoid.combine(andMonoid.empty, false) )
  println( andMonoid.combine(true, false) )

  println()

  println( orMonoid.combine(orMonoid.empty, true) )
  println( orMonoid.combine(orMonoid.empty, false) )
  println( orMonoid.combine(true, false) )

}