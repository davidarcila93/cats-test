package introduction

import cats.Eq
import cats.instances.int._
import cats.instances.string._
import cats.syntax.eq._

object CatsEq {
  implicit val catEq: Eq[Cat] = Eq.instance( (a: Cat, b: Cat) => a.name === b.name && a.age === b.age && a.color === b.color)
}
