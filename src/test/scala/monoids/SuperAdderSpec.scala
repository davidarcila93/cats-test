package monoids

import org.scalatest.prop.{Checkers, PropertyChecks}
import org.scalatest.{FlatSpec, Matchers}
import cats.Monoid
import cats.syntax.semigroup._
import OrderInstances._

/**
  * Created by david on 15/05/17.
  */
class SuperAdderSpec extends FlatSpec with Matchers with Checkers with PropertyChecks {
  import monoids.generators.OrderGenerator._

  "Order Monoid" should "have an identity" in {
    forAll { (order: Order) =>
      order |+| Monoid[Order].empty shouldBe order
      Monoid[Order].empty |+| order shouldBe order
    }
  }

  it should "be associative" in {
    forAll { (a: Order, b: Order, c: Order) =>
      ( a |+| b ) |+| c  shouldBe  a |+| ( b  |+| c )
    }
  }

  "Supper Adder" should "add a list of Ints" in {
    import cats.instances.int._
    SuperAdder.add( List(1,2,3) ) shouldBe 6
  }

  it should "add an empty list" in {
    import cats.instances.int._
    SuperAdder.add( List[Int]() ) shouldBe 0
  }

  it should "add a list of Orders" in {
    import OrderInstances._
    SuperAdder.add( List( Order(1, 2), Order(3.5, 4) ) ) shouldBe Order(4.5, 6)
  }

}
