package util

import pt.med.wallet.domain.core.{PasswordConf, SessionConf}

trait ConfigResources {
  implicit val sessionConf: SessionConf = SessionConf()
  implicit val passwordCong: PasswordConf = PasswordConf(
    8,
    15,
    withUpperCase = true,
    withLowerCase = true,
    withNumbers = true,
    withSymbols = true,
    "SECRETTESTKEY123"
  )
}
