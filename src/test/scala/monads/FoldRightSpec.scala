package monads

import org.scalatest.{FlatSpec, Matchers}
import monads.FoldRight._

/**
  * Created by david on 17/05/17.
  */
class FoldRightSpec extends FlatSpec with Matchers {

  "Naive fold right" should "work for a small list" in {
    val n = 5000
    naiveFoldRight( ( 1 to n ).map( _ => 1 ).toList, 0 )( _ + _ ) shouldBe n

  }

  it should "blow the stack for a big list" in {
    val n = 100000
    an [StackOverflowError] should be thrownBy {
      naiveFoldRight( ( 1 to n ).toList, 0 )( _ + _ )
    }
  }

  "Safe fold right" should "work for a big list" in {
    val n = 100000
    safeFoldRight( ( 1 to n ).map( _ => 1 ).toList, 0 )( _ + _ ).value shouldBe n
  }

}
