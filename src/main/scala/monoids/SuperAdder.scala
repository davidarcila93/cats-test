package monoids

import cats.instances.double._
import cats.Semigroup
import cats.Monoid
import cats.syntax.semigroup._
import cats.instances.int._
import cats.instances.option._

object SuperAdder {

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    items match {
      case h :: t => h |+| add(t)
      case nil => monoid.empty
    }
  }
}

case class Order(totalCost: Double, quantity: Double)

object OrderInstances {
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    def empty: Order = Order(0, 0)
    def combine(x: Order, y: Order): Order = Order( x.totalCost |+| y.totalCost, x.quantity |+| y.quantity )
  }
}
