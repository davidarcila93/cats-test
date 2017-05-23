package transformers

import cats.data.EitherT
import cats.syntax.applicative._
import cats.instances.either._
import cats.instances.future._


import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import scala.concurrent.ExecutionContext.Implicits.global

object Transformers {

  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] = powerLevels.get(autobot) match {
    case Some(p) => p.pure[Response]
    case _ => EitherT[Future, String, Int](Future.successful(Left(s"$autobot was unreachable.")))
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      p1 <- getPowerLevel(ally1)
      p2 <- getPowerLevel(ally2)
    } yield p1 + p2 > 15

  def tacticalReport(ally1: String, ally2: String): String = {
    val move: Future[Either[String, Boolean]] = canSpecialMove(ally1, ally2).value

    val result: Either[String, Boolean] = Await.result( move, Duration.Inf )

    result match {
      case Left(error) => error
      case Right(can) =>if (can) s"$ally1 and $ally2 are ready to roll out!" else s"$ally1 and $ally2 need a recharge."
    }

  }
}
