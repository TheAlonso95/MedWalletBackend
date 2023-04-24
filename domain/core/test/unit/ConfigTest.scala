package unit

import org.scalatest.flatspec._
import org.scalatest.matchers._
import pt.med.wallet.domain.core.exceptions.ConfigException
import pt.med.wallet.domain.core.{PasswordConf, SessionConf}

import scala.concurrent.duration._
import scala.language.postfixOps

class ConfigTest extends AnyFlatSpec with should.Matchers {
  "A session configuration" should "parse correctly the config values present on the test.conf" in {
      val sessionConf = SessionConf.load("test.conf")
      val expectedConfig = SessionConf(cookieName = "med_cookie", expirationTime = 24 hours, secureCookie = false)
      sessionConf shouldBe expectedConfig
    }

    "A session configuration when failing to extract the config file" should "generate a session config with default values" in {
      val sessionConf = SessionConf.load("non-existent.conf")
      val expectedConfig = SessionConf()

      sessionConf shouldBe expectedConfig
    }

  "A password configuration" should "parse correctly the config values present on the test.conf" in {
    val passwordConf = PasswordConf.load("test.conf")
    val expectedConfig = PasswordConf(minLength = 8, maxLength = 30, withUpperCase = true, secretKey = "secret_key_test_123")
    passwordConf shouldBe expectedConfig
  }

  "A session password when failing to extract the config file" should "generate a session config with default values" in {
    assertThrows[ConfigException]{
      PasswordConf.load("failure.conf")
    }

  }
}
