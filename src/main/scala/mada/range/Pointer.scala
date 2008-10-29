
package mada.range


trait Pointer[E] {
// element-access
    protected def _read: E = { throw NotReadable(this) }
    protected def _write(e: E): Unit = { throw NotWritable(this) }
    final def read: E = _read
    final def write(e: E): Pointer[E] = { _write(e); this }

// traversal
    protected def _traversalTag: TraversalTag
    final def traversalTag = _traversalTag

// single-pass
    protected def _equals(that: Pointer[E]): Boolean
    protected def _increment: Unit
    final def ===(that: Pointer[E]): Boolean = _equals(that)
    final def !==(that: Pointer[E]): Boolean = !(this === that)
    final def ++ : Unit = _increment
    override final def equals(that: Any): Boolean = that match {
        case that: Pointer[E] => this === that.asInstanceOf[Pointer[E]]
        case _ => false
    }

// forward
    protected def _clone: Pointer[E] = { throw NotForward(this) }
    protected def _hashCode: Int = { throw NotForward(this) }
    override final def clone(): Pointer[E] = _clone
    final def +++ : Pointer[E] = { val tmp = this.clone(); this++; tmp }
    override final def hashCode = _hashCode

// bidirectional
    protected def _decrement: Unit = { throw NotBidirectional(this) }
    final def -- : Unit = _decrement
    final def --- : Pointer[E] = { val tmp = this.clone(); this--; tmp }

// random-access
    protected def _offset(n: Long): Unit = { throw NotRandomAccess(this) }
    protected def _difference(that: Pointer[E]): Long = { throw NotRandomAccess(this) }
    final def - (that: Pointer[E]): Long = _difference(that)
    final def +=(n: Long): Pointer[E] = { _offset(n); this }
    final def -=(n: Long): Pointer[E] = this += (-n)
    final def + (n: Long): Pointer[E] = clone() += n
    final def - (n: Long): Pointer[E] = clone() -= n
    final def < (that: Pointer[E]): Boolean = _difference(that) < 0
    final def apply(n: Long): E = (this + n).read

// Range construction
    def ~(that: Pointer[E]) = new Range[E] {
        override def _begin = Pointer.this
        override def _end = that
    }

// as Output
    def toOutput = new Output[E] {
        override def _write(e: E) = { Pointer.this.write(e); Pointer.this++; }
    }
}


trait TraversalTag
case class SinglePassTraversalTag extends TraversalTag
case class ForwardTraversalTag extends SinglePassTraversalTag
case class BidirectionalTraversalTag extends ForwardTraversalTag
case class RandomAccessTraversalTag extends BidirectionalTraversalTag


case class NotReadable[E](pointer: Pointer[E]) extends UnsupportedOperationException
case class NotWritable[E](pointer: Pointer[E]) extends UnsupportedOperationException

case class NotForward[E](pointer: Pointer[E]) extends UnsupportedOperationException
case class NotBidirectional[E](private val p: Pointer[E]) extends NotForward[E](p)
case class NotRandomAccess[E](private val p: Pointer[E]) extends NotBidirectional[E](p)
