
package mada.range


trait Pointer[A] {
// element-access
    protected def _read: A = { throw NotReadable(this) }
    protected def _write(e: A): Unit = { throw NotWritable(this) }
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
    protected def _clone: Pointer[A] = { throw NotForward(this) }
    protected def _hashCode: Int = { throw NotForward(this) }
    override final def clone: Pointer[A] = _clone
    override final def hashCode = _hashCode
    final def /++ : Pointer[A] = { val tmp = clone; this++/; tmp }

// bidirectional
    protected def _decrement: Unit = { throw NotBidirectional(this) }
    final def --/ : Pointer[A] = { _decrement; this }
    final def /-- : Pointer[A] = { val tmp = clone; this--/; tmp }

// random-access
    protected def _offset(d: Long): Unit = { throw NotRandomAccess(this) }
    protected def _difference_(that: Pointer[A]): Long = { throw NotRandomAccess(this) }
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

// PointerRange construction
    def <=<(that: Pointer[A]): PointerRange[A] = new PointerRange(this, that)

// as Output
    def toOutput = new Output[A] {
        override def _write(e: A) = { Pointer.this.write(e); Pointer.this++/; }
    }

// utilities
    final def swap(that: Pointer[A]) = {
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

    final def cloneIn(t: Traversal): Pointer[A] = if (traversal conformsTo t) clone else this
}

// dereference
object * {
    def apply[A](p: Pointer[A]): A = p.read
    def update[A](p: Pointer[A], e: A): Unit = p.write(e)
}


case class NotReadable[A](pointer: Pointer[A]) extends UnsupportedOperationException
case class NotWritable[A](pointer: Pointer[A]) extends UnsupportedOperationException

case class NotForward[A](pointer: Pointer[A]) extends UnsupportedOperationException
case class NotBidirectional[A](private val p: Pointer[A]) extends NotForward[A](p)
case class NotRandomAccess[A](private val p: Pointer[A]) extends NotBidirectional[A](p)
