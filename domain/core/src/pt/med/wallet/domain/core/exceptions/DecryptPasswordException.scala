package pt.med.wallet.domain.core.exceptions

case class DecryptPasswordException(override val msg: String) extends PasswordException(msg)
