package monoids.definition

import org.scalatest.prop.{Checkers, PropertyChecks}
import org.scalatest.{FlatSpec, Matchers}
import BooleanMonoids._

/**
  * Created by david on 15/05/17.
  */
class MonoidSpec extends FlatSpec with Matchers with Checkers with PropertyChecks {

  "andMonoid" should "have an identity" in {
    forAll { (b: Boolean) =>
      andMonoid.combine(andMonoid.empty, b) shouldBe b
      andMonoid.combine(b, andMonoid.empty) shouldBe b
    }
  }

  it should "be associative" in {
    forAll { (a: Boolean, b: Boolean, c: Boolean) =>
      val left = andMonoid.combine( andMonoid.combine(a, b), c )
      val right = andMonoid.combine( a, andMonoid.combine(b, c) )
      left == right shouldBe true
    }
  }

  "orMonoid" should "have an identity" in {
    forAll { (b: Boolean) =>
      orMonoid.combine(orMonoid.empty, b) shouldBe b
      orMonoid.combine(b, orMonoid.empty) shouldBe b
    }
  }

  it should "be associative" in {
    forAll { (a: Boolean, b: Boolean, c: Boolean) =>
      val left = orMonoid.combine( orMonoid.combine(a, b), c )
      val right = orMonoid.combine( a, orMonoid.combine(b, c) )
      left == right shouldBe true
    }
  }

}
