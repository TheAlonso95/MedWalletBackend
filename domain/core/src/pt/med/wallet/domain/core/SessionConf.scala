package pt.med.wallet.domain.core

import pureconfig.ConfigSource
import pureconfig.generic.auto._
import org.slf4j.LoggerFactory
import org.slf4j.Logger

import scala.concurrent.duration._
import scala.language.postfixOps

case class SessionConf(
                        cookieName: String = "session_id",
                        secretKey: String = "my_secret_key",
                        expirationTime: FiniteDuration = 30 minutes,
                        cookiePath: String = "/",
                        cookieDomain: Option[String] = None,
                        secureCookie: Boolean = true
                      )

object SessionConf {

  private val logger: Logger = LoggerFactory.getLogger(getClass.getName)
  def load: SessionConf = ConfigSource.default.load[SessionConf].getOrElse {
    logger.warn("SessionConf was set with default values")
    SessionConf()
  }
}
