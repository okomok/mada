
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
    def equal[B](p: Pointer[B], g: (A, B) => Boolean) = Equal(this, p, g)
    def equals(that: Range[A]) = Equals(this, that)
    def equals[B](that: Range[B], g: (A, B) => Boolean) = Equals(this, that, g)
    def filter(g: A => Boolean) = Filter(this, g)
    def forEach(g: A => Any) = ForEach(this, g)
    def joint(that: Range[A]) = Joint(this, that)
    def outdirect = Outdirect(this)
    def transform[B](g: A => B) = Transform(this, g)
    def size = Size(this)

    def ->[To](g: RangeFunction[To]): To = (g.fromRange[A])(this)
    def ->(g: RangeTransformation): Range[A] = (g.fromRange[A])(this)

    def toIterator: Iterator[A] = new RangeIterator(this)
}
