package pt.med.wallet.domain.core

import pureconfig.generic.auto._
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import pt.med.wallet.Config

import scala.concurrent.duration._
import scala.language.postfixOps

case class SessionConf(
                        cookieName: String = "session_id",
                        expirationTime: FiniteDuration = 30 minutes,
                        cookiePath: String = "/",
                        cookieDomain: Option[String] = None,
                        secureCookie: Boolean = true
                      )

object SessionConf extends Config[SessionConf] {

  private val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  def load(resource: String): SessionConf =
    getConfig(resource, "session-conf").getOrElse {
      logger.warn("SessionConf was set with default values")
      SessionConf()
    }
}
