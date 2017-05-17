package monads

import cats.data.Writer
import cats.syntax.writer._
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.Seq
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


/**
  * Created by david on 17/05/17.
  */
class FactorialSpec extends FlatSpec with Matchers {

  "Factorial writer" should "separate logs" in {
    val result: Seq[Writer[Vector[String], Int]] = Await.result(Future.sequence(Vector(
      Future(Factorial.factorial(3)),
      Future(Factorial.factorial(3))
    )), Duration.Inf)

    result shouldBe Vector( 6.writer(Vector("fact 1 1", "fact 2 2", "fact 3 6")), 6.writer(Vector("fact 1 1", "fact 2 2", "fact 3 6")) )
  }

}
