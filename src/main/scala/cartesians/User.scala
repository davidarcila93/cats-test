package cartesians

import cats.data.Validated
import cats.instances.vector._
import cats.syntax.cartesian._
import cats.syntax.either._

import scala.util.Try

/**
  * Created by david on 25/05/17.
  */
case class User(name: String, age: Int)

object User {

  private def getValue(field: String, map: Map[String, String]): Either[String, String] =
    Either.fromOption(map get field, s"missing field $field")

  private def parseInt(str: String): Either[String, Int] = Either.fromTry(Try(str.toInt)).leftMap(_.getMessage)

  private def nonBlank(str: String): Either[String, String] = Either.right[String, String](str).ensure("string must be non empty")(!_.isEmpty)

  private def nonNegative(int: Int): Either[String, Int] = Either.right[String, Int](int).ensure("int must be non negative")(_ >= 0)

  private def readName(map: Map[String, String]): Either[String, String] =
    for {
      str <- getValue("name", map)
      name <- nonBlank(str)
    } yield name

  private def readAge(map: Map[String, String]): Either[String, Int] =
    for {
      str <- getValue("age", map)
      int <- parseInt(str)
      age <- nonNegative(int)
    } yield age

  def validate(map: Map[String, String]): Validated[Vector[String], User] = {
    val nameValidated: Validated[Vector[String], String] = readName(map).leftMap(Vector(_)).toValidated
    val ageValidated: Validated[Vector[String], Int] = readAge(map).leftMap(Vector(_)).toValidated
    (nameValidated |@| ageValidated).map((name, age) => User(name, age))
  }

}
