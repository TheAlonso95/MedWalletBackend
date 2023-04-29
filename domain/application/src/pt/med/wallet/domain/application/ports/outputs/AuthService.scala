package pt.med.wallet.domain.application.ports.outputs

import pt.med.wallet.domain.application.http.requests._
import pt.med.wallet.domain.application.http.responses._

import scala.concurrent.Future

trait AuthService {
  def signIn(request: AccountRequest): Future[Unit]

  def updateAccount(request: UpdateAccountRequest, token: String): Future[Unit]

  def deleteAccount(request: DeleteAccountRequest, token: String): Future[Unit]

  def login(request: LoginRequest): Future[LoginResponse]

  def validateSession(request: ValidateSessionRequest): Future[Boolean]

  def logout(request: LogoutRequest): Future[Unit]
}
