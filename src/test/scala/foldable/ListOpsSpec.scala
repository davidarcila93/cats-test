package foldable

import org.scalatest.prop.{Checkers, PropertyChecks}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by david on 30/05/17.
  */
class ListOpsSpec extends FlatSpec with Matchers with Checkers with PropertyChecks {

  import ListOps._

  "ListOps map" should "work as List map" in {
    val addOne: Int => Int = _ + 1
    forAll { (list: List[Int]) =>
      list map addOne shouldBe map(list, addOne)
    }
  }

  "ListOps flatMap" should "work as List flatMap" in {
    val nextInts: Int => List[Int] = (x) => List(x + 1, x + 2)
    forAll { (list: List[Int]) =>
      list flatMap nextInts shouldBe flatMap(list, nextInts)
    }
  }

  "ListOps filter" should "work as List filter" in {
    val even: Int => Boolean = _ % 2 == 0
    forAll { (list: List[Int]) =>
      list filter even shouldBe filter(list, even)
    }
  }

}
