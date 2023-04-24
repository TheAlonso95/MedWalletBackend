package pt.med.wallet.domain.core

import org.slf4j.{Logger, LoggerFactory}
import pt.med.wallet.Config
import pt.med.wallet.domain.core.exceptions.ConfigException
import pureconfig.generic.auto._


case class PasswordConf(minLength: Short = 0,
                        maxLength: Short = Short.MaxValue,
                        withUpperCase: Boolean = false,
                        withLowerCase: Boolean = false,
                        withNumbers: Boolean = false,
                        withSymbols: Boolean = false,
                        secretKey: String
                       )

object PasswordConf extends Config[PasswordConf] {

  private val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  def load(resource: String): PasswordConf =
    getConfig(resource, "password-conf") match {
      case Left(failures) =>
        logger.error("Password configuration failed to load", ConfigException(failures.prettyPrint()))
        throw ConfigException(failures.prettyPrint())
      case Right(config) =>
        logger.info("Password configurations were set with success")
        config
    }
}