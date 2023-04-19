package pt.med.wallet.domain.core

import pureconfig._
import pureconfig.generic.auto._
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import pt.med.wallet.domain.core.exceptions.ConfigException


case class PasswordConf(minLength: Short = 0,
                        maxLength: Short = Short.MaxValue,
                        withUpperCase: Boolean = false,
                        withLowerCase: Boolean = false,
                        withNumbers: Boolean = false,
                        withSymbols: Boolean = false,
                        secretKey: String
                       )

object PasswordConf {

  private val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  def load: PasswordConf = ConfigSource.default.load[PasswordConf] match {
    case Left(failures) =>
      logger.error("Password configuration failed to load", ConfigException(failures.prettyPrint()))
      throw ConfigException(failures.prettyPrint())
    case Right(config) =>
      logger.info("Password configurations were set with success")
      config
  }
}