package pt.med.wallet.domain.application.http.requests

import play.api.libs.json.{Json, OFormat}
import pt.med.wallet.domain.application.utils.AuthFormatter
import pt.med.wallet.domain.core.PasswordConf
import pt.med.wallet.domain.core.valueobjects.{Email, Password}

case class UpdateAccountRequest(
                                 email: String,
                                 password: String,
                                 newPassword: Option[String] = None,
                                 newEmail: Option[String] = None
                               ) {

  def isPasswordChangeRequest: Boolean = newPassword.isDefined

  def isEmailChangeRequest: Boolean = !isPasswordChangeRequest

  def toDomainPassword(implicit config: PasswordConf): Password = Password(newPassword.get, isEncrypted = true)

  def toDomainEmail: Email = Email(newEmail.get)

}

object UpdateAccountRequest extends AuthFormatter {
  implicit val format: OFormat[UpdateAccountRequest] = Json.format[UpdateAccountRequest]
}
