package pt.med.wallet.domain.application.http.requests

import play.api.libs.json.{Json, OFormat}
import pt.med.wallet.domain.application.utils.AuthFormatter

case class UpdateAccountRequest(
                                 email: String,
                                 password: String,
                                 newPassword: Option[String] = None,
                                 newEmail: Option[String] = None
                               ) {

  def isPasswordChangeRequest: Boolean = newPassword.isDefined

  def isEmailChangeRequest: Boolean = newPassword.isEmpty

}

object UpdateAccountRequest extends AuthFormatter {
  implicit val format: OFormat[UpdateAccountRequest] = Json.format[UpdateAccountRequest]
}
