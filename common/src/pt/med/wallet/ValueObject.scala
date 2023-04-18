package pt.med.wallet

abstract class ValueObject[A](field: A) {
  override def hashCode(): Int = field.hashCode()

  override def equals(obj: Any): Boolean = obj match {
    case other: ValueObject[_] => field == other.field
    case _ => false
  }
}
