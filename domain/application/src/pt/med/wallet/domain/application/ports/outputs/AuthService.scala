package pt.med.wallet.domain.application.ports.outputs

import pt.med.wallet.domain.application.http.requests.{AccountRequest, DeleteAccountRequest, LoginRequest, SuspendAccountRequest, UpdateAccountRequest}
import pt.med.wallet.domain.application.http.responses.LoginResponse

import scala.concurrent.Future

trait AuthService {
  def signIn(request: AccountRequest): Future[Unit]

  def updatePassword(request: UpdateAccountRequest): Future[Unit]

  def updateEmail(request: UpdateAccountRequest): Future[Unit]

  def deleteAccount(request: DeleteAccountRequest): Future[Unit]

  def suspendAccount(request: SuspendAccountRequest, reasons: List[String]): Future[Unit]

  def login(request: LoginRequest): Future[LoginResponse]

  def logout(request: String): Future[Unit]
}
