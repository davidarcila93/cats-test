package cartesians

import org.scalatest.{FlatSpec, Matchers}
import User._
import cats.data.Validated
import cats.syntax.validated._

/**
  * Created by david on 25/05/17.
  */
class UserSpec extends FlatSpec with Matchers {

  "User validate" should "succeed" in {
    validate(Map("name" -> "David", "age" -> "23")) shouldBe User("David", 23).valid
  }

  it should "succeed if zero age" in {
    validate(Map("name" -> "David", "age" -> "0")) shouldBe User("David", 0).valid
  }

  it should "fail if negative age" in {
    validate(Map("name" -> "David", "age" -> "-1")) shouldBe Vector("int must be non negative").invalid
  }

  it should "fail if empty name" in {
    validate(Map("name" -> "", "age" -> "10")) shouldBe Vector("string must be non empty").invalid
  }

  it should "fail if empty name and negative age" in {
    val validatedUser = validate(Map("name" -> "", "age" -> "-10"))
    validatedUser should be invalid
    val errors: Set[String] = validatedUser.fold[Vector[String]](v => v, _ => Vector()).toSet
    errors should contain ("string must be non empty")
    errors should contain ("int must be non negative")
  }

}
