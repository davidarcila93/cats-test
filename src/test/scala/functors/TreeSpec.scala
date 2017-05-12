package functors

import org.scalatest.{FlatSpec, Matchers}
import Tree._
import cats.syntax.functor._

/**
  * Created by david on 12/05/17.
  */
class TreeSpec extends FlatSpec with Matchers {

  "The tree functor" should "map over a Leaf" in {
    leaf(3) map {
      _ + 2
    } shouldBe leaf(5)
  }

  it should "map over a Branch" in {
    branch(leaf(1), branch(leaf(2), leaf(3))) map {
      _ + 1
    } shouldBe branch(leaf(2), branch(leaf(3), leaf(4)))
  }

}
