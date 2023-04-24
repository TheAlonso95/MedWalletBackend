package pt.med.wallet

abstract class Entity[A](val id: Id[_]) {
  override def hashCode(): Int = id.hashCode()

  override def equals(obj: Any): Boolean = obj match {
    case other: Entity[_] => id == other.id
    case _ => false
  }
}
