package pt.med.wallet.domain.core.exceptions

import pt.med.wallet.DomainException

case class PasswordException(msg: String) extends DomainException(msg)
