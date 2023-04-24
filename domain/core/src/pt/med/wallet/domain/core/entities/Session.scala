package pt.med.wallet.domain.core.entities

import org.slf4j.{Logger, LoggerFactory}
import pt.med.wallet.Entity
import pt.med.wallet.domain.core.SessionConf
import pt.med.wallet.domain.core.valueobjects.{Email, SessionId}

import java.time.LocalDateTime
import java.util.{Base64, UUID}

case class Session(sessionId: SessionId, token: String, expirationDate: LocalDateTime)
  extends Entity[Session](sessionId) {

  def isValid: Boolean = expirationDate.isAfter(LocalDateTime.now())
}

object Session {

  private val logger: Logger = LoggerFactory.getLogger(getClass.getName)
  def generateSession(email: Email)(implicit config: SessionConf): Session = {
    logger.info(s"Generating a session for email: $email")
    val id = UUID.randomUUID()
    val token = Base64.getEncoder.encodeToString(s"$email-token-$id".getBytes)
    logger.info(s"Encoded token: $token")

    val expirationConfig = config.expirationTime
    val expirationAt = LocalDateTime.now().plus(expirationConfig.length, expirationConfig.unit.toChronoUnit)
    logger.info(s"Setting expiration time to: $expirationAt")
    Session(new SessionId(id), token, expirationAt)
  }
}
