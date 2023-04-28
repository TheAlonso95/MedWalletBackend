package pt.med.wallet.domain.application.http.requests

import play.api.libs.json.{Json, OFormat}
import pt.med.wallet.domain.application.utils.AuthFormatter

case class LoginRequest(email: String, password: String)

object LoginRequest extends AuthFormatter {
  implicit val format: OFormat[LoginRequest] = Json.format[LoginRequest]
}
