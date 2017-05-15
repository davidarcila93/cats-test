package introduction

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by david on 12/05/17.
  */
class CatSpec extends FlatSpec with Matchers {

  "Cats printable instance" should "format Cat" in {
    val cat = Cat("Meow", 5, "white")

    import PrintableCatInstance._
    import PrintSyntax._

    cat.format shouldBe "Meow is a 5 year-old white cat."
  }

  "Cats show instance" should "format Cat" in {
    val cat = Cat("Meow", 5, "white")

    import CatsShow._
    import cats.syntax.show._

    cat.show shouldBe "Meow is a 5 year-old white cat."
  }

  "Cats eq instance" should "compare equality of two cats" in {

    import CatsEq._
    import cats.Eq
    import cats.syntax.eq._
    import cats.instances.option._

    val cat1 = Cat("Garfield", 35, "orange and black")
    val cat2 = Cat("Heathcliff", 30, "orange and black")

    Eq[Cat].eqv(cat1, cat2) shouldBe false
    cat1 =!= cat2 shouldBe true

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    Eq[Option[Cat]].eqv(optionCat1, optionCat2) shouldBe false

  }

}
