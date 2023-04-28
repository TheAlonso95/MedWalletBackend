package pt.med.wallet.domain.application.http.requests

import play.api.libs.json.{Json, OFormat}
import pt.med.wallet.domain.application.utils.AuthFormatter
import pt.med.wallet.domain.core.PasswordConf
import pt.med.wallet.domain.core.entities.Account
import pt.med.wallet.domain.core.valueobjects.{AccountId, Email, Password}

import java.util.UUID

case class AccountRequest(email: String, password: String) {

  def toDomainAccount(implicit conf: PasswordConf): Account = Account(new AccountId(UUID.randomUUID()), Email(email), Password(password, isEncrypted = true))
}

object AccountRequest extends AuthFormatter {
  implicit val format: OFormat[AccountRequest] = Json.format[AccountRequest]
}
