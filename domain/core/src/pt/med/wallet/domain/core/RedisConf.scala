package pt.med.wallet.domain.core

import org.slf4j.{Logger, LoggerFactory}
import pt.med.wallet.Config
import pureconfig.generic.auto._

case class RedisConf(host: String = "localhost", port: Int = 6379)

object RedisConf extends Config[RedisConf] {

  private val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  def load(resource: String): RedisConf = getConfig(resource, "redis-conf").getOrElse {
    logger.warn("SessionConf was set with default values")
    RedisConf()
  }
}