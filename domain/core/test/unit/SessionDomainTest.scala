package unit

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._
import pt.med.wallet.domain.core.SessionConf
import pt.med.wallet.domain.core.entities.Session
import pt.med.wallet.domain.core.valueobjects.Email
import util.ConfigResources

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.Base64

import scala.concurrent.duration._
import scala.language.postfixOps

class SessionDomainTest extends AnyFlatSpec with should.Matchers with ConfigResources {
  val validEmail: Email = Email("med@domain.com")
  lazy val session: Session = Session.generateSession(validEmail)

  "For a valid email a session" should "be generated containing the correct data of the email on the token" in {
    val decodedToken = new String(Base64.getDecoder.decode(session.token))

    decodedToken.startsWith(validEmail.email) shouldBe true
  }

  "For a valid email a session" should "be generated containing the correct data of the id on the token" in {
    val sessionId: String = session.sessionId.toString
    val decodedToken = new String(Base64.getDecoder.decode(session.token))

    decodedToken.endsWith(sessionId) shouldBe true
  }

  "For a valid email a session" should "be generated containing the correct expiration date" in {
    val expirationDate = Session.generateSession(validEmail).expirationDate
    val expectedExpirationDate = LocalDateTime.now.plus(expirationConf.length, expirationConf.unit.toChronoUnit)

    val unitAffected = expirationConf.unit.toChronoUnit match {
      case ChronoUnit.MINUTES => (expirationDate.getMinute, expectedExpirationDate.getMinute)
      case ChronoUnit.HOURS => (expirationDate.getHour, expectedExpirationDate.getHour)
      case ChronoUnit.DAYS => (expirationDate.getDayOfYear, expectedExpirationDate.getDayOfYear)
    }

    unitAffected match {
      case (test, expected) => test.compare(expected) shouldBe 0
    }
  }

  "For a valid email a session" should "be generated containing a valid token" in {
    val session: Session = Session.generateSession(validEmail)

    session.isValid shouldBe true
  }

  "Id a session for a valid email expires than the token" should "not be valid anymore" in {
    val fakeSessionConf = new SessionConf(expirationTime = 1 second)
    val expiredSession = Session.generateSession(validEmail)(fakeSessionConf)
    val oneSecond = 1000
    Thread.sleep(oneSecond)

    expiredSession.isValid shouldBe false
  }
}
