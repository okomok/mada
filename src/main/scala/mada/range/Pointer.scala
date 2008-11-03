
package mada.range


trait Pointer[A] {
// element-access
    protected def _read: A = { throw ErrorNotReadable(this) }
    protected def _write(e: A): Unit = { throw ErrorNotWritable(this) }
    final def read: A = _read
    final def write(e: A): Pointer[A] = { _write(e); this }

// traversal
    protected def _traversal: Traversal
    final def traversal = _traversal

// single-pass
    protected def _increment: Unit
//  override def equals(that: Any): Boolean
    final def ++/ : Pointer[A]  = { _increment; this }

// forward
    protected def _clone: Pointer[A] = { throw ErrorNotForward(this) }
    protected def _hashCode: Int = { throw ErrorNotForward(this) }
    override final def clone: Pointer[A] = _clone
    override final def hashCode = _hashCode
    final def /++ : Pointer[A] = { val tmp = clone; this++/; tmp }

// bidirectional
    protected def _decrement: Unit = { throw ErrorNotBidirectional(this) }
    final def --/ : Pointer[A] = { _decrement; this }
    final def /-- : Pointer[A] = { val tmp = clone; this--/; tmp }

// random-access
    protected def _offset(d: Long): Unit = { throw ErrorNotRandomAccess(this) }
    protected def _difference_(that: Pointer[A]): Long = { throw ErrorNotRandomAccess(this) }
    final def - (that: Pointer[A]): Long = _difference_(that)
    final def +=(d: Long): Pointer[A] = { _offset(d); this }
    final def -=(d: Long): Pointer[A] = this += (-d)
    final def + (d: Long): Pointer[A] = clone += d
    final def - (d: Long): Pointer[A] = clone -= d
    final def < (that: Pointer[A]): Boolean = this - that < 0
    final def apply(d: Long): A = (this + d).read
    final def update(d: Long, e: A): Unit = (this + d).write(e)

// debug
    protected def _invariant: Unit = { }

// utilities
    final def advance(d : Long) = PointerAdvance(this, d)
    final def swap(that: Pointer[A]) = PointerSwap(this, that)
    def toImmutable: Pointer[A] = new ImmutablePointer(this)
    final def <=<(that: Pointer[A]): PointerRange[A] = new PointerRange(this, that)

// as Output
    def toOutput = new Output[A] {
        override def _write(e: A) = { Pointer.this.write(e); Pointer.this++/; }
    }
}

// dereference
object * {
    def apply[A](p: Pointer[A]): A = p.read
    def update[A](p: Pointer[A], e: A): Unit = p.write(e)
}


case class ErrorNotReadable[A](pointer: Pointer[A]) extends UnsupportedOperationException
case class ErrorNotWritable[A](pointer: Pointer[A]) extends UnsupportedOperationException

case class ErrorNotForward[A](pointer: Pointer[A]) extends UnsupportedOperationException
case class ErrorNotBidirectional[A](private val p: Pointer[A]) extends ErrorNotForward[A](p)
case class ErrorNotRandomAccess[A](private val p: Pointer[A]) extends ErrorNotBidirectional[A](p)
