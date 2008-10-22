
package mada.cursor


trait TraversalTag
case class SinglePass extends TraversalTag
case class Forward extends SinglePass
case class Bidirectional extends Forward
case class RandomAccess extends Bidirectional

object NotImplemented extends UnsupportedOperationException


trait Cursor {
  type Type
  type Element
  type Difference = Long

  protected def traversalTag: TraversalTag
  protected def read: Element = { throw NotImplemented }
  protected def write(e: Element): Unit = { throw NotImplemented }
  protected def equal(that: Type): Boolean
  protected def increment: Unit
  protected def copy: Type = { throw NotImplemented }
  protected def decrement: Unit = { throw NotImplemented }
  protected def offset(d: Difference): Unit = { throw NotImplemented }
  protected def difference(that: Type): Difference = { throw NotImplemented }

  override def equals(that: Any): Boolean = this equal (that.asInstanceOf[Type])
  final def get: Element = this read
  final def ++ : Unit = this increment
  final def += (n: Difference): Unit = this offset n
  final def -  (that: Type): Difference = this difference that
  final def <  (that: Type): Boolean = (this difference that) < 0
}

object foo {
  def bar(c: Cursor): Cursor#Element = { val v: Cursor#Element = c.get ; v }
}
