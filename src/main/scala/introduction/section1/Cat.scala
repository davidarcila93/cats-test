package introduction.section1

case class Cat(name: String, age: Int, color: String)

object PrintableCatInstance {
  implicit val PrintableCat = new Printable[Cat] {
    def format(a: Cat): String = s"${a.name} is a ${a.age} year-old ${a.color} cat."
  }
}

object MyApp extends App{
  val cat = Cat("Meow", 5, "white")

  import PrintableCatInstance._
  import PrintSyntax._
  cat.print
}