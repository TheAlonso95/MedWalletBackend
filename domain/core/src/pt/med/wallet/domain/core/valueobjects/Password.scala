package pt.med.wallet.domain.core.valueobjects

import pt.med.wallet.ValueObject
import pt.med.wallet.domain.core.PasswordConf
import pt.med.wallet.domain.core.exceptions.PasswordException

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher
import java.util.Base64

case class Password(password: String)(implicit config: PasswordConf) extends ValueObject[String](password) {
  private val algorithm = "AES"
  private val transformation = s"$algorithm/ECB/PKCS5Padding"
  private val keySize = 256

  def encrypt: Password = {
    val cipher = Cipher.getInstance(transformation)
    val key = new SecretKeySpec(config.secretKey.getBytes("UTF-8"), 0, keySize / 8, algorithm)

    cipher.init(Cipher.ENCRYPT_MODE, key)

    val encryptedBytes = cipher.doFinal(password.getBytes("UTF-8"))

    this.copy(Base64.getEncoder.encodeToString(encryptedBytes))
  }

  def decrypt(secret: String, cipherText: String): Password = {
    val cipher = Cipher.getInstance(transformation)
    val key = new SecretKeySpec(secret.getBytes("UTF-8"), 0, keySize / 8, algorithm)

    cipher.init(Cipher.DECRYPT_MODE, key)

    val encryptedBytes = Base64.getDecoder.decode(cipherText)
    this.copy(new String(cipher.doFinal(encryptedBytes), "UTF-8"))
  }

}

object Password {
  def apply(password: String)(implicit config: PasswordConf): Password = {
    if(isPasswordValid(password, config)) new Password(password)
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
