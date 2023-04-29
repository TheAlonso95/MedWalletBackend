package pt.med.wallet.domain.application.ports.inputs

import pt.med.wallet.domain.application.dtos.AccountDTO

import scala.concurrent.Future

trait AuthRepository {

  type Token = String
  type Empty = Unit

  def createAccount(account: AccountDTO): Future[Empty]
  def updatePassword(email: String, password: String, token: Token)(newPassword: String): Future[Empty]
  def updateEmail(email: String, password: String, token: Token)(newEmail: String): Future[Empty]
  def deleteAccount(email: String, password: String, isSoftDelete: Boolean = true, token: Token): Future[Empty]
  def createSession(email: String, password: String): Future[Token]
  def deleteSession(sessionId: String): Future[Empty]
}
