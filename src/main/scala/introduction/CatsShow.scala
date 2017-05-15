package introduction

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

object CatsShow {

  implicit val catShow: Show[Cat] = Show.show( cat => s"${cat.name.show} is " +
    s"a ${cat.age.show} year-old ${cat.color.show} cat.")

}
