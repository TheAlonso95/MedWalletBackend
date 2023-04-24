package pt.med.wallet.domain.application.dtos

import pt.med.wallet.domain.core.entities.Session
import pt.med.wallet.domain.core.valueobjects.SessionId

import java.time.LocalDateTime
import java.util.UUID

case class SessionDTO(sessionId: UUID, token: String, expirationDate: LocalDateTime) {
  def toSession: Session = Session(new SessionId(sessionId), token, expirationDate)
}


object SessionDTO {

  def fromSession(session: Session): SessionDTO = SessionDTO(session.sessionId.id, session.token, session.expirationDate)

  def fromOptSession(optSession: Option[Session]): Option[SessionDTO] = optSession.map { session =>
    SessionDTO(session.sessionId.id, session.token, session.expirationDate)
  }

}
