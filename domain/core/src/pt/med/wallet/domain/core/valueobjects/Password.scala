package pt.med.wallet.domain.core.valueobjects

import pt.med.wallet.ValueObject
import pt.med.wallet.domain.core.PasswordConf
import pt.med.wallet.domain.core.exceptions.PasswordException

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher
import java.util.Base64

abstract class Password(password: String)(implicit config: PasswordConf) extends ValueObject[String](password) {
  final val UTF_8: String = "UTF-8"
  private val algorithm = "AES"
  private val transformation = s"$algorithm/ECB/PKCS5Padding"
  private val keySize = 256

  val cipher: Cipher = Cipher.getInstance(transformation)
  val key = new SecretKeySpec(config.secretKey.getBytes(UTF_8), 0, keySize / 8, algorithm)

  def encrypt: Password

  def decrypt: Password

  def isEncrypted: Boolean
}

final case class Encrypted(password: String)(implicit config: PasswordConf) extends Password(password) {
  override def encrypt: Password =
    throw new IllegalAccessError("You can not encrypt this password since is already encrypted")

  override def decrypt: Password = {

    cipher.init(Cipher.DECRYPT_MODE, key)

    val encryptedBytes = Base64.getDecoder.decode(password)

    Decrypted(new String(cipher.doFinal(encryptedBytes), UTF_8))
  }

  override def isEncrypted: Boolean = true
}

case class Decrypted(password: String)(implicit config: PasswordConf) extends Password(password) {

  override def encrypt: Password = {

    cipher.init(Cipher.ENCRYPT_MODE, key)

    val encryptedBytes = cipher.doFinal(password.getBytes(UTF_8))

    Encrypted(Base64.getEncoder.encodeToString(encryptedBytes))
  }

  override def decrypt: Password =
    throw new IllegalAccessError("You can not decrypt this password since is already decrypted")

  override def isEncrypted: Boolean = false
}

object Password {
  def apply(password: String, isEncrypted: Boolean = false)(implicit config: PasswordConf): Password = {
    if (isPasswordValid(password, config))
      if (isEncrypted) Encrypted(password) else Decrypted(password)
    else throw PasswordException(s"The password: $password is not valid for the current password configuration")
  }

  private def isValidLength(implicit config: PasswordConf, password: String): Boolean = {
    lazy val max = config.maxLength
    lazy val min = config.minLength
    val length = password.length

    min <= length && length <= max
  }

  private def areValidSymbols(implicit config: PasswordConf, password: String): Boolean = {
    if (!config.withSymbols) true
    else {
      val symbols = "~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?"
      password.exists(letter => symbols.contains(letter))
    }
  }

  private def areValidLowerCases(implicit config: PasswordConf, password: String): Boolean =
    if (!config.withUpperCase) true else password.exists(_.isUpper)

  private def areValidUpperCases(implicit config: PasswordConf, password: String): Boolean =
    if (!config.withLowerCase) true else password.exists(_.isLower)

  private def areDigitsValid(implicit config: PasswordConf, password: String): Boolean =
    if (!config.withNumbers) true else password.exists(_.isDigit)

  private def isPasswordValid(implicit password: String, config: PasswordConf): Boolean = {
    isValidLength && areValidSymbols && areValidLowerCases &&
      areValidLowerCases && areValidUpperCases && areDigitsValid
  }
}
