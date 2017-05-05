package chapter1.section3

import chapter1.section1.Cat
import cats.Eq
import cats.syntax.eq._
import cats.instances.option._
import cats.instances.int._
import cats.instances.string._

object catsInstances {
  implicit val catEq: Eq[Cat] = Eq.instance( (a: Cat, b: Cat) => a.name === b.name && a.age === b.age && a.color === b.color)
}

object CatsEq extends App{
  import catsInstances._

  val cat1 = Cat("Garfield", 35, "orange and black")
  val cat2 = Cat("Heathcliff", 30, "orange and black")
  println(cat1 === cat2)
  println(cat1 =!= cat2)
  println(cat1 =!= cat1)

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]
  println(optionCat1 === optionCat2)
  println(optionCat1 =!= optionCat2)
  println(optionCat1 =!= optionCat1)
}
