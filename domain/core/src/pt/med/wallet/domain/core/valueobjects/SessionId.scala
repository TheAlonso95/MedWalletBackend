package pt.med.wallet.domain.core.valueobjects

import pt.med.wallet.Id

import java.util.UUID
class SessionId(id: UUID) extends Id[UUID](id)