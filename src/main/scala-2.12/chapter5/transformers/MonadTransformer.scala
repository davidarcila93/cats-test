package chapter5.transformers

import cats.data.{EitherT, OptionT}
import cats.instances.future._
import cats.instances.list._
import cats.syntax.applicative._
import cats.syntax.traverse._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Servers {
  type FutureEither[A] = EitherT[Future, String, A]

  val loadAverages = Map(
    "a.example.com" -> 0.1,
    "b.example.com" -> 0.5,
    "c.example.com" -> 0.2
  )

  def getLoad(hostname: String): FutureEither[Double] = {
    val averageOption = loadAverages.get(hostname)
    averageOption match {
      case Some(avg) => avg.pure[FutureEither]
      case _ => EitherT.left[Future, String, Double](Future.successful(s"The host $hostname was unreachable"))
    }
  }

  def getMeanLoad(hostnames: List[String])/*: FutureEither[Double]*/ = {
    val listFutureEither: FutureEither[List[Double]] = hostnames.map{ getLoad }.sequence[FutureEither, Double]
    listFutureEither map { _.sum / hostnames.length }

//    val map: Seq[Future[Either[String, Double]]] = hostnames.map{ getLoad(_).value }
//    val sequence: Future[Seq[Either[String, Double]]] = Future.sequence(map)

  }

}

object Test {
  type ListOption[A] = OptionT[List, A]

  val a: OptionT[List, Int] = OptionT.liftF(List(1, 2))
  val b: OptionT[List, Int] = OptionT.liftF(List(3, 4))
  def operation: OptionT[List, Int] = for{
    x <- a
    y <- b
  } yield x + y

}

object Main extends App{
  import Servers._
  println(getLoad("a.example.com"))
  val load = getLoad("a.example.om")
  Await.result( load.value, Duration.Inf )
  println(load)

  println()
  private val mean = getMeanLoad(List("a.example.com", "b.example.com", "c.example.com"))
  Await.result( mean.value, Duration.Inf )
  println(mean)
}
