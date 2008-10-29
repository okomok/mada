
package mada.range


trait Pointer[E] {
    type PointerType = Pointer[E]
    type ElementType = E

// element-access
    protected def _read: ElementType = { throw NotReadable(this) }
    protected def _write(e: ElementType): Unit = { throw NotWritable(this) }
    final def read: ElementType = _read
    final def write(e: ElementType): PointerType = { _write(e); this }

// traversal
    protected def _traversalTag: TraversalTag[ElementType]
    final def traversalTag = _traversalTag

// single-pass
    protected def _equals(that: PointerType): Boolean
    protected def _increment: Unit
    final def ===(that: PointerType): Boolean = _equals(that)
    final def !==(that: PointerType): Boolean = !(this === that)
    final def ++ : Unit = _increment
    override final def equals(that: Any): Boolean = that match {
        case that: PointerType => this === that.asInstanceOf[PointerType]
        case _ => false
    }

// forward
    protected def _clone: PointerType = { throw NotForward(this) }
    protected def _hashCode: Int
    override final def clone(): PointerType = _clone
    final def +++ : PointerType = { val tmp = this.clone(); this++; tmp }
    override final def hashCode = _hashCode

// bidirectional
    protected def _decrement: Unit = { throw NotBidirectional(this) }
    final def -- : Unit = _decrement
    final def --- : PointerType = { val tmp = this.clone(); this--; tmp }

// random-access
    type DifferenceType = Long
    protected def _offset(n: DifferenceType): Unit = { throw NotRandomAccess(this) }
    protected def _difference(that: PointerType): DifferenceType = { throw NotRandomAccess(this) }
    final def - (that: PointerType): DifferenceType = _difference(that)
    final def +=(n: DifferenceType): PointerType = { _offset(n); this }
    final def -=(n: DifferenceType): PointerType = this += (-n)
    final def + (n: DifferenceType): PointerType = clone() += n
    final def - (n: DifferenceType): PointerType = clone() -= n
    final def < (that: PointerType): Boolean = _difference(that) < 0
    final def apply(n: DifferenceType): ElementType = (this + n).read

// Range construction
    def ~(that: PointerType) = new Range[ElementType] {
        override def _begin = Pointer.this
        override def _end = that
    }

// as Output
    implicit def toOutput = new Output[ElementType] {
        override def _write(e: ElementType) = { Pointer.this._write(e); Pointer.this.++; }
    }
}


abstract class TraversalTag[E](pointer: Pointer[E])
case class SinglePass[E](private val p: Pointer[E]) extends TraversalTag[E](p)
case class Forward[E](private val p: Pointer[E]) extends SinglePass[E](p)
case class Bidirectional[E](private val p: Pointer[E]) extends Forward[E](p)
case class RandomAccess[E](private val p: Pointer[E]) extends Bidirectional[E](p)


case class NotReadable[E](pointer: Pointer[E]) extends UnsupportedOperationException
case class NotWritable[E](pointer: Pointer[E]) extends UnsupportedOperationException
case class NotForward[E](pointer: Pointer[E]) extends UnsupportedOperationException
case class NotBidirectional[E](private val p: Pointer[E]) extends NotForward[E](p)
case class NotRandomAccess[E](private val p: Pointer[E]) extends NotBidirectional[E](p)
