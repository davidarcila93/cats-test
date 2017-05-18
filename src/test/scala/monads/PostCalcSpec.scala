package monads

import monads.PostCalc._
import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by david on 18/05/17.
  */
class PostCalcSpec extends FlatSpec with Matchers {

  "PostCalc eval One" should "compose correctly" in {
    val program = for {
      _ <- evalOne("1")
      _ <- evalOne("2")
      ans <- evalOne("+")
    } yield ans
    program.runA(Nil).value shouldBe 3
  }

  "PostCalc eval All" should "calculate operations correctly" in {
    evalAll(List("1", "2", "+", "3", "*")).runA(Nil).value shouldBe 9
  }


}
