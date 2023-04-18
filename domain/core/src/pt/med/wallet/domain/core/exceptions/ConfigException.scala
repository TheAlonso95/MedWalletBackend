package pt.med.wallet.domain.core.exceptions

import pt.med.wallet.DomainException

case class ConfigException(msg: String) extends DomainException(msg)
