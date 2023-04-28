package pt.med.wallet.domain.application.http.responses

import play.api.libs.json.{Json, OFormat}
import pt.med.wallet.domain.application.utils.AuthFormatter

case class LoginResponse(token: String)

object LoginResponse extends AuthFormatter {
  implicit val format: OFormat[LoginResponse] = Json.format[LoginResponse]
}
