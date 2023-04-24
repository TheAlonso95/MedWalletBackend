package pt.med.wallet.domain.core.exceptions

case class EncryptPasswordException(override val msg: String) extends PasswordException(msg)
