package foldable

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by david on 30/05/17.
  */
class FoldableSpec extends FlatSpec with Matchers {

  "FoldLeft" should "reverse a list" in {
    List(1, 2, 3).foldLeft(List[Int]())( (acc, item) => item :: acc ) shouldBe List(3,2,1)
  }

  "FoldRight" should "leave a list unchanged" in {
    List(1, 2, 3).foldRight(List[Int]())( (item, acc) => item :: acc ) shouldBe List(1,2,3)
  }

}
