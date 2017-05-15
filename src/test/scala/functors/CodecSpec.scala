package functors

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by david on 15/05/17.
  */
class CodecSpec extends FlatSpec with Matchers {

  "Int codec instance" should "encode an Int" in {
    import CodecInstances._
    Codec.encode(2) shouldBe "2"
    Codec.encode(100) shouldBe "100"
  }

  it should "decode a valid String" in {
    import CodecInstances._

    Codec.decode("2") shouldBe Option(2)
    Codec.decode("100") shouldBe Option(100)
  }

  it should "decode an invalid String to a None" in {
    import CodecInstances._

    Codec.decode("2a") shouldBe None
    Codec.decode("z") shouldBe None
  }
}
