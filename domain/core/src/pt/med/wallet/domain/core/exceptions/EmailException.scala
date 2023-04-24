package pt.med.wallet.domain.core.exceptions

import pt.med.wallet.DomainException

case class EmailException(msg: String) extends DomainException(msg)
