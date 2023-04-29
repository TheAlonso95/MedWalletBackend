package pt.med.wallet.persistence.parsers

import com.redis.serialization.Parse
import play.api.libs.json.{Json, Reads}
import pt.med.wallet.domain.application.dtos.{AccountDTO, SessionDTO}

object Parsers {
  private def parseAccount(bytes: Array[Byte]): AccountDTO = {
    val json = Json.parse(bytes)
    val reads = Reads.of[AccountDTO]
    reads.reads(json).getOrElse(throw new IllegalArgumentException("Failed to parse AccountDTO"))
  }

  private def parseSession(bytes: Array[Byte]): SessionDTO = {
    val json = Json.parse(bytes)
    val reads = Reads.of[SessionDTO]
    reads.reads(json).getOrElse(throw new IllegalArgumentException("Failed to parse AccountDTO"))
  }

  implicit val parseAccountDTO: Parse[AccountDTO] = new Parse[AccountDTO](parseAccount)
  implicit val parseSessionDTO: Parse[SessionDTO] = new Parse[SessionDTO](parseSession)
}
