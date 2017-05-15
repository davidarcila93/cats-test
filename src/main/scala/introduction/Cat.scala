package introduction

case class Cat(name: String, age: Int, color: String)

object PrintableCatInstance {
  implicit val PrintableCat = new Printable[Cat] {
    def format(a: Cat): String = s"${a.name} is a ${a.age} year-old ${a.color} cat."
  }
}
