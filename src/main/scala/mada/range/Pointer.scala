
package mada.range


trait Pointer[E] {
// element-access
    protected def _read: E = { throw NotReadable(this) }
    protected def _write(e: E): Unit = { throw NotWritable(this) }
    final def read: E = _read
    final def write(e: E): Pointer[E] = { _write(e); this }

// traversal
    protected def _traversal: Traversal
    final def traversal = _traversal

// single-pass
    protected def _increment: Unit
//  override def equals(that: Any): Boolean
    final def ++/ : Pointer[E]  = { _increment; this }

// forward
    protected def _clone: Pointer[E] = { throw NotForward(this) }
    protected def _hashCode: Int = { throw NotForward(this) }
    override final def clone: Pointer[E] = _clone
    override final def hashCode = _hashCode
    final def /++ : Pointer[E] = { val tmp = clone; this++/; tmp }

// bidirectional
    protected def _decrement: Unit = { throw NotBidirectional(this) }
    final def --/ : Pointer[E] = { _decrement; this }
    final def /-- : Pointer[E] = { val tmp = clone; this--/; tmp }

// random-access
    protected def _offset(d: Long): Unit = { throw NotRandomAccess(this) }
    protected def _difference_(that: Pointer[E]): Long = { throw NotRandomAccess(this) }
    final def - (that: Pointer[E]): Long = _difference_(that)
    final def +=(d: Long): Pointer[E] = { _offset(d); this }
    final def -=(d: Long): Pointer[E] = this += (-d)
    final def + (d: Long): Pointer[E] = clone += d
    final def - (d: Long): Pointer[E] = clone -= d
    final def < (that: Pointer[E]): Boolean = this - that < 0
    final def apply(d: Long): E = (this + d).read
    final def update(d: Long, e: E): Unit = (this + d).write(e)

// debug
    protected def invariant: Boolean = true
    protected def compatible(that: Pointer[E]): Boolean = true

// Range construction
    def <=<(that: Pointer[E]): Range[E] = new PointerRange(this, that)

// as Output
    def toOutput = new Output[E] {
        override def _write(e: E) = { Pointer.this.write(e); Pointer.this++/; }
    }

// utilities
    final def swap(that: Pointer[E]) = {
        val tmp = read
        write(that.read);
        that.write(tmp)
    }

    final def advance(d_ : Long): Unit = {
        var d = d_
        traversal match {
            case RandomAccessTraversal() => this += d
            case BidirectionalTraversal() => {
                if (d >= 0)
                    while (d != 0) { this++/; d = d - 1 }
                else
                    while (d != 0) { this--/; d = d + 1 }
            }
            case _ => while (d != 0) { this++/; d = d - 1 }
        }
    }

    final def cloneIn(t: Traversal): Pointer[E] = if (traversal conformsTo t) clone else this
}

// dereference
object * {
    def apply[E](p: Pointer[E]): E = p.read
    def update[E](p: Pointer[E], e: E): Unit = p.write(e)
}


case class NotReadable[E](pointer: Pointer[E]) extends UnsupportedOperationException
case class NotWritable[E](pointer: Pointer[E]) extends UnsupportedOperationException

case class NotForward[E](pointer: Pointer[E]) extends UnsupportedOperationException
case class NotBidirectional[E](private val p: Pointer[E]) extends NotForward[E](p)
case class NotRandomAccess[E](private val p: Pointer[E]) extends NotBidirectional[E](p)
