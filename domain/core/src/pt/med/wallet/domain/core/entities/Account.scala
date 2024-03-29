package pt.med.wallet.domain.core.entities

import pt.med.wallet.{AggregateRoot, Entity}
import pt.med.wallet.domain.core.valueobjects._

case class Account(accountId: AccountId, email: Email, password: Password, session: Option[Session] = None)
  extends Entity[Account](accountId) with AggregateRoot[Account]
