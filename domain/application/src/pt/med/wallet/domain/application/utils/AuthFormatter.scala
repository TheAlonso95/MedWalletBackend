package pt.med.wallet.domain.application.utils

import play.api.libs.json.{Json, JsonConfiguration}
import play.api.libs.json.JsonConfiguration.Aux
import play.api.libs.json.JsonNaming.SnakeCase

trait AuthFormatter {
  implicit val config: Aux[Json.MacroOptions] = JsonConfiguration(SnakeCase)
}
