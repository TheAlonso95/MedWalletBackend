package unit

import org.scalatest.flatspec._
import org.scalatest.matchers._
import pt.med.wallet.domain.core.exceptions.EmailException
import pt.med.wallet.domain.core.valueobjects.{Decrypted, Email, Password}
import util.ConfigResources

import scala.language.postfixOps

class AccountDomainTest extends AnyFlatSpec with should.Matchers with ConfigResources {
  //Email tests
  "If the email is valid than" should "return the class Email with success" in {
    val validEmail: Email = Email("med@domain.com")
    val expectedValue: Email = new Email("med@domain.com")

    validEmail shouldBe expectedValue
  }

  "If the email is not valid than" should "throw an Email exception" in {
    assertThrows[EmailException] {
     val failureEmail: Email = Email("med")
    }

    assertThrows[EmailException] {
      val failureEmail: Email = Email("med@domain")
    }

    assertThrows[EmailException] {
      val failureEmail: Email = Email("marco@domain.")
    }
  }

  //Password tests
  "If the password is valid than" should "return the class Password with success" in {
    val validPassword = Password("#tvHd07ol94l")
    val expectedValue = Decrypted("#tvHd07ol94l")

    validPassword shouldBe expectedValue
  }

  "An encrypted password" should "be able to decrypt to the original password" in {
    val originalPassword = Decrypted("#tvHd07ol94l")
    val encryptedPassword = originalPassword.encrypt
    val decryptPassword = encryptedPassword.decrypt

    decryptPassword shouldBe originalPassword
  }
}
