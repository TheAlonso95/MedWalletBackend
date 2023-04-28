package pt.med.wallet.domain.application.http.requests

import play.api.libs.json.{Json, OFormat}
import pt.med.wallet.domain.application.utils.AuthFormatter

case class DeleteAccountRequest(email: String, password: String, isSoftDelete: Boolean)

object DeleteAccountRequest extends AuthFormatter {
  implicit val format: OFormat[DeleteAccountRequest] = Json.format[DeleteAccountRequest]
}
