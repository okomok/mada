
package mada.rng


trait Pointer[A] {
// element-access
    protected def _read: A = { throw new ErrorNotReadable(this) }
    protected def _write(e: A) { throw new ErrorNotWritable(this) }
    final def read: A = _read
    final def write(e: A): Pointer[A] = { _write(e); this }

// traversal
    protected def _traversal: Traversal
    final def traversal = _traversal

// single-pass
    protected def _increment { throw new ErrorNotSinglePass(this) }
//  override def equals(that: Any): Boolean
    final def pre_++ : Pointer[A] = { _increment; this }

// forward
    protected def _clone: Pointer[A] = { throw new ErrorNotForward(this) }
    protected def _hashCode: Int = { throw new ErrorNotForward(this) }
    override final def clone: Pointer[A] = _clone
    override final def hashCode = _hashCode
    final def ++ : Pointer[A] = { val tmp = clone; pre_++; tmp }

// bidirectional
    protected def _decrement { throw new ErrorNotBidirectional(this) }
    final def pre_-- : Pointer[A] = { _decrement; this }
    final def -- : Pointer[A] = { val tmp = clone; pre_--; tmp }

// random-access
    protected def _offset(d: Long) { throw new ErrorNotRandomAccess(this) }
    protected def _difference_(that: Pointer[A]): Long = { throw new ErrorNotRandomAccess(this) }
    final def - (that: Pointer[A]): Long = _difference_(that)
    final def +=(d: Long): Pointer[A] = { _offset(d); this }
    final def -=(d: Long): Pointer[A] = this += (-d)
    final def + (d: Long): Pointer[A] = clone += d
    final def - (d: Long): Pointer[A] = clone -= d
    final def < (that: Pointer[A]): Boolean = this - that < 0
    final def > (that: Pointer[A]): Boolean = this - that > 0
    final def <= (that: Pointer[A]): Boolean = this - that <= 0
    final def >= (that: Pointer[A]): Boolean = this - that >= 0
    final def apply(d: Long): A = (this + d).read
    final def update(d: Long, e: A) { (this + d).write(e) }

// debug
    protected def _invariant { }

// utilities
    final def advance(d: Long) = detail.PointerAdvance(this, d)
    final def output: A => Pointer[A] = { (e: A) => write(e); pre_++ }
    final def swap(that: Pointer[A]) = detail.PointerSwap(this, that)
    final def <=<(that: Pointer[A]) = new PointerRng(this, that)
    final def cloneIn(t: Traversal): Pointer[A] = if (traversal <:< t) clone else this
}


object * {
    def apply[A](p: Pointer[A]): A = p.read
    def update[A](p: Pointer[A], e: A) { p.write(e) }
}

object ++ {
    def apply[A](p: Pointer[A]): Pointer[A] = p.pre_++
}

object -- {
    def apply[A](p: Pointer[A]): Pointer[A] = p.pre_--
}


class ErrorNotReadable[A](val pointer: Pointer[A]) extends UnsupportedOperationException
class ErrorNotWritable[A](val pointer: Pointer[A]) extends UnsupportedOperationException

class ErrorNotSinglePass[A](val pointer: Pointer[A]) extends UnsupportedOperationException
class ErrorNotForward[A](p: Pointer[A]) extends ErrorNotSinglePass[A](p)
class ErrorNotBidirectional[A](p: Pointer[A]) extends ErrorNotForward[A](p)
class ErrorNotRandomAccess[A](p: Pointer[A]) extends ErrorNotBidirectional[A](p)
