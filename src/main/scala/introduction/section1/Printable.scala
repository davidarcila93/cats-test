package introduction.section1

trait Printable[A] {
  def format(a: A): String
}

object PrintableInstances {
  implicit val printableString = new Printable[String] {
    def format(a: String): String = a
  }
  implicit val printableInt = new Printable[Int] {
    def format(a: Int): String = "" + a
  }
}

object PrintSyntax {

  implicit class PrintOps[A](a: A) {

    def format(implicit printable: Printable[A]): String = {
      printable.format(a)
    }

    def print(implicit printable: Printable[A]): Unit = {
      println( a.format )
    }
  }
}