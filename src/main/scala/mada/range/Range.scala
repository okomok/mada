
package mada.range


// Traversal Tags

trait TraversalTag
case class SinglePass extends TraversalTag
case class Forward extends SinglePass
case class Bidirectional extends Forward
case class RandomAccess extends Bidirectional

object NotImplemented extends UnsupportedOperationException


// Pointer trait

trait Pointer {
  type PointerType <: Pointer
//  def lower: PointerType = { self: PointerType => self } // doesn't compile

  type ElementType
  type DifferenceType = Long // means BigInt can't be RandomAccess.
  protected def _traversalTag: TraversalTag
  protected def _read: ElementType = { throw NotImplemented }
  protected def _write(e: ElementType): Unit = { throw NotImplemented }
  protected def _equals(that: PointerType): boolean
  protected def _increment: Unit
  protected def _copy: PointerType = { throw NotImplemented }
  protected def _decrement: Unit = { throw NotImplemented }
  protected def _offset(d: DifferenceType): Unit = { throw NotImplemented }
  protected def _difference(that: PointerType): DifferenceType = { throw NotImplemented }

  final def traversalTag = _traversalTag
  override def equals(that: Any) = this _equals (that.asInstanceOf[PointerType])
  final def read: ElementType = this _read
  final def ++ : Unit = this _increment
  final def += (n: DifferenceType) = this _offset n
  final def -  (that: PointerType) = this _difference that
  final def <  (that: PointerType) = (this _difference that) < 0

  final def to(last: PointerType) = { self: PointerType => new PointerRange[PointerType](self, last) }
}


// PointerRange

class PointerRange[C <: Pointer](first: C, last: C) extends Range {
  type PointerType = C
  override def _begin = first
  override def _end = last
}


// Range trait

trait Range {
  type PointerType <: Pointer
  type ElementType = PointerType#ElementType
  type DifferenceType = PointerType#DifferenceType

  protected def _begin: PointerType
  protected def _end: PointerType

  final def begin = _begin
  final def end = _end
  final def traversalTag = begin.traversalTag
}


abstract
class RangeIterator[A](rng: Range) extends Iterator[Range#ElementType] {

}

class IteratorPointer[A](_it: Iterator[A], _isEnd: boolean) extends Pointer {
  private val it = _it
  private var a: Option[A] = if (it.hasNext) new Some(it.next) else None // null can't be used.
  private var ends = if (_isEnd) true else it.hasNext

  type PointerType = IteratorPointer[A]
  override type ElementType = A
  override def _traversalTag = new SinglePass
  override def _read: A = a.get
  override def _equals(that: PointerType): Boolean = (this ends) == (that ends)
  override def _increment: Unit = { a = new Some(it.next) }
}

object fromIterator {
  def apply[I](i: Iterator[I]) = new IteratorPointer(i, false) to new IteratorPointer(i, true)
}


/*
object Conversions { // maybe useless
  implicit def iterator2cursor[A](it: Iterator[A]) = new IteratorPointer(it)
}
*/

object foo {
  def bar(c: Pointer): Pointer#ElementType = { val v: Pointer#ElementType = c.read ; v }
}
