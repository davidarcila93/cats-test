package monads

import cats.data.Reader
import cats.syntax.applicative._
/**
  * Created by david on 17/05/17.
  */
case class Db(usernames: Map[Int, String], passwords: Map[String, String])

object Db {
  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).exists( _ == password ))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      usernameOpt <- findUsername(userId)
      cp <- usernameOpt.map {
        checkPassword( _, password )
      }.getOrElse( false.pure[DbReader] )
    } yield cp
}

