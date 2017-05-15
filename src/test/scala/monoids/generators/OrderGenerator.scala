package monoids.generators

import monoids.Order
import org.scalacheck.{Arbitrary, Gen}

/**
  * Created by david on 15/05/17.
  */
object OrderGenerator {
  val orderGenerator: Gen[Order] = for {
    a <- Gen.choose(0, 1000)
    b <- Gen.choose(0, 1000)
  } yield Order(a, b)

  implicit val arbitraryOrder: Arbitrary[Order] = Arbitrary(orderGenerator)
}
