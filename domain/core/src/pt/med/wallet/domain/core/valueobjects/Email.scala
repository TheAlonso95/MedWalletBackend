package pt.med.wallet.domain.core.valueobjects

import org.slf4j.{Logger, LoggerFactory}
import pt.med.wallet.ValueObject
import pt.med.wallet.domain.core.exceptions.EmailException

case class Email(email: String) extends ValueObject[String](email)

object Email {

  private val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  def apply(email: String): Email = {
    if(isValidEmail(email)) new Email(email) else {
      logger.error(s"This email: $email is not valid")
      throw EmailException(s"This email: $email is not valid")
    }
  }

  def apply(name: String)(domain: String): Email = {
    val email: String = s"$name@$domain"
    Email(email)
  }

  private def isValidEmail(email: String): Boolean = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
}
