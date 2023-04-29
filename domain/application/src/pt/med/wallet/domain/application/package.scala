package pt.med.wallet.domain

import scala.util.{Failure, Success, Try}

package object application {

  implicit class TryEnriched[A](t: Try[A]) {
    def withDefault: A = t match {
      case Failure(exception) => throw exception
      case Success(value) => value
    }
  }

}
