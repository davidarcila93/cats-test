package functors

import org.scalatest.{FlatSpec, Matchers}

import Printable._


/**
  * Created by david on 12/05/17.
  */
class PrintableSpec extends FlatSpec with Matchers {

  "Int printable instance" should "format int" in {

    import StringPrintableInstance._

    format(1) shouldBe "\"int 1\""

  }

  "Boolean printable instance" should "format boolean" in {

    import PrintableInstances._

    format(true) shouldBe "yes"
    format(false) shouldBe "no"
  }

}
