package introduction.section2
import cats.Show
import introduction.section1.Cat
import cats.syntax.show._
import cats.instances.int._
import cats.instances.string._

object ShowCats extends App {

  implicit val catShow: Show[Cat] = Show.show( cat => s"${cat.name.show} is " +
    s"a ${cat.age.show} year-old ${cat.color.show} cat.")

  val cat = Cat("Meow", 5, "white")
  println(cat.show)

}
