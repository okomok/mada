
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
  protected def _clone: PointerType = { throw NotImplemented }
  protected def _decrement: Unit = { throw NotImplemented }
  protected def _offset(d: DifferenceType): Unit = { throw NotImplemented }
  protected def _difference(that: PointerType): DifferenceType = { throw NotImplemented }

  final def traversalTag = _traversalTag
  override def equals(that: Any) = this _equals (that.asInstanceOf[PointerType])
  override def clone: PointerType = this _clone
  final def read = this _read
  final def write(e: ElementType) = this _write e
  final def ++ : Unit = this _increment
  final def -- : Unit = this _decrement
  final def += (n: DifferenceType) = this _offset n
  final def -  (that: PointerType) = this _difference that
  final def <  (that: PointerType) = (this _difference that) < 0

  final def to(last: PointerType) = { self: PointerType => new PointerRange[PointerType](self, last) }
}


trait PointerAdapter extends Pointer {
  type Type = PointerAdapter
  type UnderlyingPointerType <: Pointer
  protected def _underlying: UnderlyingPointerType
  def underlying = _underlying

  override type PointerType <: Type
  override def _traversalTag = underlying traversalTag
//  override def _read = underlying read
//  override def _write(e: ElementType) = this.underlying.write(e) //
  override def _equals(that: PointerType) = underlying equals that.underlying
  override def _increment = underlying ++
  override def _decrement = underlying --
  override def _offset(d: DifferenceType) = underlying += d
  override def _difference(that: PointerType) = { self: PointerType => self.underlying - that.underlying }
}

class BBBB[P](p: P) {
  def 

class TransformPointer[P <: Pointer, R](p: P, f: Function1[P#ElementType, R]) extends PointerAdapter {
  type UnderlyingPointerType = P
  def call_clone: P = p.clone().asInstanceOf[P]
  override def underlying:P = p
  override type PointerType = TransformPointer[P, R]
  override type ElementType = R
  override def _read = f(p.read)
  override def _clone = new TransformPointer[P, R](p.clone, f)
}

// PointerRange

class PointerRange[P <: Pointer](first: P, last: P) extends Range {
  type PointerType = P
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


object foo {
  def bar(c: Pointer): Pointer#ElementType = { val v: Pointer#ElementType = c.read ; v }
}

object filter {
  def apply[R <: Range](r: R, f: Function1[R#ElementType, boolean]) =
    new PointerRange[R#PointerType](r begin, r end) // (r begin) to (r end) doesn't compile
}





