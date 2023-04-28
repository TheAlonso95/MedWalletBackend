package pt.med.wallet.domain.application.ports.inputs

import pt.med.wallet.domain.application.dtos.AccountDTO

import scala.concurrent.Future

trait AuthRepository {

  type Token = String
  type Empty = Unit
  def createAccount(account: AccountDTO): Future[Empty]
  def updatePassword(accountDTO: AccountDTO)(newPassword: String): Future[Empty]
  def updateEmail(accountDTO: AccountDTO)(newEmail: String): Future[Empty]
  def deleteAccount(accountDTO: AccountDTO, isSoftDelete: Boolean = true): Future[Empty]
  def suspendAccount(accountDTO: AccountDTO, reasons: List[String]): Future[Empty]
  def createSession(email: String, password: String): Future[Token]
  def deleteSession(token: String): Future[Empty]
}
