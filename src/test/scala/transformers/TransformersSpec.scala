package transformers

import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by david on 23/05/17.
  */
class TransformersSpec extends FlatSpec with Matchers {

  import Transformers._

  "Power Level" should "return Left if autobot not in the map" in {
    val result = Await.result(getPowerLevel("Ironhide").value, Duration.Inf)
    result shouldBe Left("Ironhide was unreachable.")
  }

  "Tactical Report" should "be printed correctly" in {
    tacticalReport("Jazz", "Bumblebee") shouldBe "Jazz and Bumblebee need a recharge."

    tacticalReport("Bumblebee", "Hot Rod") shouldBe "Bumblebee and Hot Rod are ready to roll out!"

    tacticalReport("Jazz", "Ironhide") shouldBe "Ironhide was unreachable."
  }

}
