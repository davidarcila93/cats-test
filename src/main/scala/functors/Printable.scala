package functors

/**
  * Created by david on 11/05/17.
  */
object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def contramap[A, B](f: A => B)(implicit pb: Printable[B]): Printable[A] = pb.contramap(f)
}

object PrintableInstances {
  implicit val stringPrintable =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }
  implicit val booleanPrintable =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }
}

object StringPrintableInstance {

  import Printable._
  import PrintableInstances._

  implicit val intPrintable: Printable[Int] = contramap(n => s"int $n")
}

trait Printable[A] {
  self =>
  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = new Printable[B] {
    def format(value: B): String = self.format(func(value))
  }
}
