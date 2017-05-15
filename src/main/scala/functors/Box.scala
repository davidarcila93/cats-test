package functors

/**
  * Created by david on 15/05/17.
  */
final case class Box[A](value: A)

object BoxInstances {
  implicit def PrintableBox[A](implicit p: Printable[A]): Printable[Box[A]] = new Printable[Box[A]] {
    override def format(box: Box[A]): String = p.format(box.value)
  }

  implicit def BoxCodec[A](implicit c: Codec[A]): Codec[Box[A]] = c.imap[Box[A]](Box(_), _.value)
}