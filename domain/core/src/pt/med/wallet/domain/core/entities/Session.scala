package pt.med.wallet.domain.core.entities

import pt.med.wallet.Entity
import pt.med.wallet.domain.core.valueobjects.SessionId

import java.time.LocalDateTime

case class Session(sessionId: SessionId, token: String, expirationDate: LocalDateTime) extends Entity[SessionId]
