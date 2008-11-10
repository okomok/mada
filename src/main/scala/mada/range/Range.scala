
package mada.range


trait Range[A] {
    protected def _begin: Pointer[A]
    protected def _end: Pointer[A]

    final def begin = _begin
    final def end = _end
    final lazy val traversal = begin.traversal
    final def models(t: Traversal): Boolean = traversal conformsTo t

    override def equals(that: Any) = equals(that.asInstanceOf[Range[A]])
    override def toString = toArrayList.toString // TODO

    def asRangeIn(t: Traversal) = detail.AsRangeIn(this, t)
    def asRangeOf[B] = (new AsRangeOf[B])(this)
    def concat(that: Range[A]) = Concat(this, that)
    def copy = FromArrayList(toArrayList)
    def copyTo[B >: A](p: Pointer[B]) = CopyTo(this, p)
    def distance = Distance(this)
    def drop(n: Long) = Drop(this, n)
    def dropWhile(f: A => Boolean) = DropWhile(this, f)
    def equals(that: Range[A]) = Equals(this, that)
    def equals[B](that: Range[B], f: (A, B) => Boolean) = Equals(this, that, f)
    def equalsTo(p: Pointer[A]) = EqualsTo(this, p)
    def equalsTo[B](p: Pointer[B], f: (A, B) => Boolean) = EqualsTo(this, p, f)
    def exists(f: A => Boolean) = Exists(this, f)
    def filter(f: A => Boolean) = Filter(this, f)
    def find(f: A => Boolean) = Find(this, f)
    def findPointerOf(f: A => Boolean) = FindPointerOf(this, f)
    def foldLeft[B](z: B, op: (B, A) => B) = FoldLeft(this, z, op)
    def forall(f: A => Boolean) = Forall(this, f)
    def foreach[X](f: A => X) = Foreach(this, f)
    def map[B](f: A => B) = Map(this, f)
    def mismatch(p: Pointer[A]) = Mismatch(this, p)
    def mismatch[B](p: Pointer[B], f: (A, B) => Boolean) = Mismatch(this, p, f)
    def offset(n1: Long, n2: Long) = Offset(this, n1, n2)
    def outdirect = Outdirect(this)
    def permutation(indices: Range[Long]) = Permutation(this, indices)
    def readOnly = ReadOnly(this)
    def reverse = Reverse(this)
    def size = Size(this)
    def slice(n: Long, m: Long) = Slice(this, n, m)
    def take(n: Long) = Take(this, n)
    def takeWhile(f: A => Boolean) = TakeWhile(this, f)
    def window(n: Long, m: Long) = Window(this, n, m)

    def toArray = ToArray(this)
    def toArrayList = ToArrayList(this)
    def toIterator: Iterator[A] = ToIterator(this)

    final def ->[X](f: RangeFunction[X]): X = (f.fromRange[A])(this)
    final def ->(f: RangeTransformation): Range[A] = (f.fromRange[A])(this)
}
