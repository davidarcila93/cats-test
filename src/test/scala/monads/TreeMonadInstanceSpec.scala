package monads

import cats.syntax.flatMap._
import cats.syntax.functor._
import functors.Tree._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by david on 18/05/17.
  */
class TreeMonadInstanceSpec extends FlatSpec with Matchers {

  import TreeMonadInstance._

  "Tree monad" should "flatmap over Tree" in {
    branch(leaf(0), leaf(10)).flatMap(n => branch(leaf(n), leaf(n + 1))) shouldBe branch(branch(leaf(0), leaf(1)), branch(leaf(10), leaf(11)))
  }

  it should "permit for comprehensions" in {
    val tree = leaf(10)
    val result = for {
      n <- tree
      k <- branch(leaf(n - 1), leaf(n + 1))
      b <- leaf(2 * k)
    } yield b

    result shouldBe branch(leaf(18), leaf(22))

  }

}
