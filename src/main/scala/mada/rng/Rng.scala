
package mada.rng


object Rng extends Conversions with Indirect with Stringize {

}


trait Rng[A] {
    protected def _begin: Pointer[A]
    protected def _end: Pointer[A]

    final def begin = _begin
    final def end = _end
    final lazy val traversal = begin.traversal
    final def models(t: Traversal): Boolean = traversal conformsTo t

    override def equals(that: Any) = equals(that.asInstanceOf[Rng[A]])
    override def toString = toArrayList.toString // TODO

    def asRngIn(t: Traversal) = AsRngIn(this, t)
    def asRngOf[B] = (new AsRngOf[B])(this)
    def concat(that: Rng[A]) = Concat(this, that)
    def copy = FromArrayList(toArrayList)
    def copyTo[B >: A](p: Pointer[B]) = detail.CopyTo(this, p)
    def distance = detail.Distance(this)
    def drop(n: Long) = Drop(this, n)
    def dropWhile(f: A => Boolean) = DropWhile(this, f)
    def equals(that: Rng[A]) = detail.Equals(this, that)
    def equals[B](that: Rng[B], f: (A, B) => Boolean) = detail.Equals(this, that, f)
    def equalsTo(p: Pointer[A]) = detail.EqualsTo(this, p)
    def equalsTo[B](p: Pointer[B], f: (A, B) => Boolean) = detail.EqualsTo(this, p, f)
    def exists(f: A => Boolean) = detail.Exists(this, f)
    def filter(f: A => Boolean) = Filter(this, f)
    def find(f: A => Boolean) = detail.Find(this, f)
    def findPointerOf(f: A => Boolean) = detail.FindPointerOf(this, f)
    def first = detail.First(this)
    def foldLeft[B](z: B, op: (B, A) => B) = detail.FoldLeft(this, z, op)
    def forall(f: A => Boolean) = detail.Forall(this, f)
    def foreach[X](f: A => X) = detail.Foreach(this, f)
    def isEmpty = detail.IsEmpty(this)
    def last = detail.Last(this)
    def map[B](f: A => B) = Map(this, f)
    def mismatch(p: Pointer[A]) = detail.Mismatch(this, p)
    def mismatch[B](p: Pointer[B], f: (A, B) => Boolean) = detail.Mismatch(this, p, f)
    def offset(n1: Long, n2: Long) = Offset(this, n1, n2)
    def outdirect = Outdirect(this)
    def permutation(indices: Rng[Long]) = Permutation(this, indices)
    def readOnly = ReadOnly(this)
    def reverse = Reverse(this)
    def size = detail.Size(this)
    def slice(n: Long, m: Long) = Slice(this, n, m)
    def take(n: Long) = Take(this, n)
    def takeWhile(f: A => Boolean) = TakeWhile(this, f)
//    def window(n: Long, m: Long) = Window(this, n, m)

    def toArray = ToArray(this)
    def toArrayList = ToArrayList(this)
//    def toIterator: Iterator[A] = ToIterator(this)
}
