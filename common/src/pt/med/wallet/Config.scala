package pt.med.wallet

import pureconfig.ConfigReader.Result
import pureconfig._

trait Config[A] {
  def getConfig(resource: String, namespace: String)(implicit reader: ConfigReader[A]): Result[A] =
    ConfigSource.resources(resource).at(namespace).load[A]
}
