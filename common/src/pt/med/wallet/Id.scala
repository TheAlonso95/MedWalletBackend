package pt.med.wallet

abstract class Id[A](val id: A) extends ValueObject[A](id) {
  override def hashCode(): Int = id.hashCode()

  override def equals(obj: Any): Boolean = obj match {
    case other: Id[_] => id == other.id
    case _ => false
  }
  override def toString: String = id.toString

}
