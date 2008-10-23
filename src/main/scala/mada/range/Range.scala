
package mada.range


// Traversal Tags

trait TraversalTag
case class SinglePass extends TraversalTag
case class Forward extends SinglePass
case class Bidirectional extends Forward
case class RandomAccess extends Bidirectional

object NotImplemented extends UnsupportedOperationException



object forceClone {
//  def apply[T](x: T): T = x.clone().asInstance[T]
}


// Pointer trait

trait Pointer[E] {
  type PointerType = Pointer[E]
  type ElementType = E
  type DifferenceType = Long // means BigInt can't be RandomAccess.
  protected def _traversalTag: TraversalTag
  protected def _read: ElementType = { throw NotImplemented }
  protected def _write(e: ElementType): Unit = { throw NotImplemented }
  protected def _equals(that: PointerType): boolean
  protected def _increment: Unit
  protected def _clone: PointerType = { throw NotImplemented }
  protected def _decrement: Unit = { throw NotImplemented }
  protected def _offset(d: DifferenceType): Unit = { throw NotImplemented }
  protected def _difference(that: PointerType): DifferenceType = { throw NotImplemented }

  final def traversalTag = _traversalTag
  override def equals(that: Any) = _equals(that.asInstanceOf[PointerType])
  override def clone(): PointerType = _clone
  final def read = _read
  final def write(e: ElementType): PointerType = { _write(e); this }
  final def ++ : PointerType = { _increment; this }
  final def -- : PointerType = { _decrement; this }
  final def += (n: DifferenceType): PointerType = { _offset(n); this }
  final def -  (that: PointerType) = _difference(that)
  final def <  (that: PointerType) = _difference(that) < 0

  final def to(last: PointerType) = new PointerRange(this, last)
}


// Range trait

trait Range[E] {
  type PointerType = Pointer[E]
  type ElementType = PointerType#ElementType
  type DifferenceType = PointerType#DifferenceType

  protected def _begin: PointerType
  protected def _end: PointerType

  final def begin = _begin
  final def end = _end
  final def traversalTag = begin.traversalTag
}


class PointerRange[E](first: Pointer[E], last: Pointer[E]) extends Range[E] {
  override def _begin = first
  override def _end = last
}


object Tester {
  def apply[E](p: Pointer[E]): Pointer[E] = p.clone
}

trait PointerAdapter[From, To] extends Pointer[To] {
  type Type = PointerAdapter[From, To]
  type UnderlyingPointerType = Pointer[From]
  protected def _underlying: UnderlyingPointerType
  def underlying = _underlying

  def lower(p: PointerType) = p.asInstanceOf[PointerAdapter[From, To]]
  override def _traversalTag = underlying traversalTag
//  override def _read = underlying read
//  override def _write(e: ElementType) = underlying write e
  override def _equals(that: PointerType) = underlying equals lower(that).underlying
  override def _increment = underlying ++
  override def _decrement = underlying --
  override def _offset(d: DifferenceType) = underlying += d
  override def _difference(that: PointerType) = this.underlying - lower(that).underlying
}


class TransformPointer[E, R](p: Pointer[E], f: Function1[E, R]) extends PointerAdapter[E, R] {
  override def _underlying = p
  override def _read = f(p.read)
  override def _clone = new TransformPointer[E, R](p.clone, f)
}



/*

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


object foo {
  def bar(c: Pointer): Pointer#ElementType = { val v: Pointer#ElementType = c.read ; v }
}
*/


object filter {
  def apply[E](r: Range[E]) = (r begin) to (r end)
}



