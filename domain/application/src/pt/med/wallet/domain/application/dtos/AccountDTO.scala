package pt.med.wallet.domain.application.dtos

import pt.med.wallet.domain.core.PasswordConf
import pt.med.wallet.domain.core.entities.Account
import pt.med.wallet.domain.core.valueobjects.{AccountId, Email, Password}

import java.util.UUID

case class AccountDTO(accountId: UUID, email: String, password: String, session: Option[SessionDTO] = None) {
  def toAccount(implicit config: PasswordConf): Account =
    Account(new AccountId(accountId), Email(email), Password(password), session.map(_.toSession))
}

object AccountDTO {
  def fromAccount(account: Account): AccountDTO =
    AccountDTO(account.accountId.id, account.email.field, account.password.field, SessionDTO.fromOptSession(account.session))
}
