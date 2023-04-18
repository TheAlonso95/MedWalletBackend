package pt.med.wallet.domain.core.entities

import pt.med.wallet.{AggregateRoot, Entity}
import pt.med.wallet.domain.core.valueobjects.AccountId

case class Account(accountId: AccountId, email: Email, password: Password, session: Option[Session] = None)
  extends Entity[AccountId] with AggregateRoot
