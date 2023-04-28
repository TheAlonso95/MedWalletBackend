package pt.med.wallet.domain.application.http.requests

import play.api.libs.json.{Json, OFormat}
import pt.med.wallet.domain.application.utils.AuthFormatter

case class SuspendAccountRequest(accountId: String, reasons: List[String])

object SuspendAccountRequest extends AuthFormatter {
  implicit val format: OFormat[SuspendAccountRequest] = Json.format[SuspendAccountRequest]
}
