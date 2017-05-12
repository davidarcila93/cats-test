package monoids.section2

import cats.Semigroup
import cats.Monoid
import cats.syntax.semigroup._
import cats.instances.int._
import cats.instances.option._
import cats.instances.double._

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

object MyApp extends App {
  println( SuperAdder.add( List[Int]() ) )
  println( SuperAdder.add( List(1,2,3) ) )

  println( SuperAdder.add( List(Option(1),Option.empty[Int],Option(3)) ) )

  import OrderInstances._
  println( SuperAdder.add( List( Order(1, 2), Order(3.5, 4) ) ) )
}