package functors

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by david on 15/05/17.
  */
class BoxSpec extends FlatSpec with Matchers {

  "Box printable instance" should "format a box of a string" in {
    import PrintableInstances._
    import BoxInstances._
    import Printable._
    val box = Box("Hello")
    format(box) shouldBe "\"Hello\""
  }

  "Box codec instance" should "encode a box to a string" in {

    import BoxInstances._
    import CodecInstances._
    import Codec._

    val box = Box(123)
    encode(box) shouldBe "123"
  }

  it should "decode a string to a box" in {

    import BoxInstances._
    import CodecInstances._
    import Codec._

    val box = Box(123)
    decode[Box[Int]]("123") shouldBe Option(box)
  }

}
