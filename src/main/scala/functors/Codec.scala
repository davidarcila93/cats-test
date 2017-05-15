package functors

import scala.util.{Success, Try}

/**
  * Created by david on 15/05/17.
  */
trait Codec[A] {
  self =>
  def encode(value: A): String

  def decode(value: String): Option[A]

  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
    new Codec[B] {
      override def encode(value: B): String = self.encode(enc(value))

      override def decode(value: String): Option[B] = self.decode(value) map dec
    }
  }
}

object Codec {

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
    c.decode(value)

}

object CodecInstances {
  implicit val intCodec: Codec[Int] = new Codec[Int] {

    def encode(value: Int): String = value.toString

    def decode(value: String): Option[Int] = Try(value.toInt) match {
      case Success(n) => Some(n)
      case _ => None
    }
  }
}
