package monads

import monads.Db._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by david on 18/05/17.
  */
class DbSpec extends FlatSpec with Matchers {

  val db = Db(
    Map(
      1 -> "dade",
      2 -> "kate",
      3 -> "margo"
    ),
    Map(
      "dade" -> "zerocool",
      "kate" -> "acidburn",
      "margo" -> "secret"
    )
  )

  "Check login" should "return true for valid credentials" in {
    checkLogin(1, "zerocool").run(db) shouldBe true
  }

  it should "return false for invalid user id" in {
    checkLogin(4, "davinci").run(db) shouldBe false
  }

}
