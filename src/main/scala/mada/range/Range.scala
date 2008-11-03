
package mada.range


trait Range[A] {
    protected def _begin: Pointer[A]
    protected def _end: Pointer[A]

    final def begin = _begin
    final def end = _end
    lazy val traversal = begin.traversal

    override def equals(that: Any) = that match {
        case that: Range[A] => equals(that)
        case _ => false
    }

    def accumulate[B](z: B, op: (B, A) => B) = Accumulate(this, z, op)
    def asRangeOf[B] = (new AsRangeOf[B])(this)
    def distance = Distance(this)
    def equal(p: Pointer[A]) = Equal(this, p)
    def equal[B](p: Pointer[B], f: (A, B) => Boolean) = Equal(this, p, f)
    def equals(that: Range[A]) = Equals(this, that)
    def equals[B](that: Range[B], f: (A, B) => Boolean) = Equals(this, that, f)
    def filter(f: A => Boolean) = Filter(this, f)
    def forEach(f: A => Any) = ForEach(this, f)
    def joint(that: Range[A]) = Joint(this, that)
    def outdirect = Outdirect(this)
    def transform[B](f: A => B) = Transform(this, f)
    def size = Size(this)

    def ->[To](f: RangeFunction[To]): To = (f.fromRange[A])(this)
    def ->(f: RangeTransformation): Range[A] = (f.fromRange[A])(this)

    def toIterator: Iterator[A] = new RangeIterator(this)
}
