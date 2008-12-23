

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Vector {
    def empty[A]: Vector[A] = vec.Empty.apply[A]
    def flatten[A](vv: Vector[Vector[A]]): Vector[A] = vec.Flatten(vv)
    def fromArray[A](u: Array[A]): Vector[A] = vec.FromArray(u)
    def fromCell[A](u: Cell[A]): Vector[A] = vec.FromCell(u)
    def fromIterator[A](u: Iterator[A]): Vector[A] = vec.FromIterator(u)
    def fromJclArrayList[A](u: java.util.ArrayList[A]): Vector[A] = vec.jcl.FromArrayList(u)
    def fromOption[A](u: Option[A]): Vector[A] = vec.FromOption(u)
    def fromRandomAccessSeq[A](u: RandomAccessSeq[A]): Vector[A] = vec.FromRandomAccessSeq(u)
    def fromString(u: String): Vector[Char] = vec.FromString(u)
    def fromValues[A](es: A*): Vector[A] = vec.FromValues(es: _*)
    def single[A](u: A): Vector[A] = vec.Single(u)
    def range(i: Int, j: Int): Vector[Int] = vec.IntRange(i, j)
    def range(i: Long, j: Long): Vector[Long] = vec.LongRange(i, j)
    def stringize(v: Vector[Char]): String = vec.Stringize(v)

    type NotReadableError[A] = vec.NotReadableError[A]
    type NotWritableError[A] = vec.NotWritableError[A]

    type Adapter[Z, A] = vec.Adapter[Z, A]
    type NotWritable[A] = vec.NotWritable[A]
}


trait Vector[A] {
    import vec._

    def size: Long
    def apply(i: Long): A = throw new NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new NotWritableError(this)

    final def vector: Vector[A] = this
    final def toPair: (Long, Long) = (0, size)
    final def toTriple: (Vector[A], Long, Long) = (this, 0, size)

    override def equals(that: Any): Boolean = Equals(this, that)
    override def toString: String = ToString(this)

    def always[B](that: Vector[B]): Vector[B] = Always(this, that)
    def append(that: Vector[A]): Vector[A] = Append(this, that)
    def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)
    def bounds: Vector[A] = Bounds(this)
    def clear: Vector[A] = Clear(this)
    def contains(e: Any): Boolean = Contains(this, e)
    def copy: Vector[A] = Copy(this)
    def cut: Vector[A] = Cut(this)
    def cycle(n: Long): Vector[A] = Cycle(this, n)
    def drop(n: Long): Vector[A] = Drop(this, n)
    def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)
    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(this, that, p)
    def exists(p: A => Boolean): Boolean = Exists(this, p)
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)
    def find(p: A => Boolean): Option[A] = Find(this, p)
    def first: A = First(this)
    def flatMap[B](f: A => Vector[B]): Vector[B] = FlatMap(this, f)
    def forall(p: A => Boolean): Boolean = Forall(this, p)
    def force: Vector[A] = Force(this)
    def foldLeft[B](z: B, op: (B, A) => B): B = FoldLeft(this, z, op)
    def foldRight[B](z: B, op: (A, B) => B): B = FoldRight(this, z, op)
    def foreach(f: A => Unit): Unit = Foreach(this, f)
    def identity: Vector[A] = Identity(this)
    def isDefinedAt(x: Long): Boolean = IsDefinedAt(this, x)
    def isEmpty: Boolean = IsEmpty(this)
    def last: A = Last(this)
    def lazy_ : Vector[A] = Lazy(this)
    def length: Long = Length(this)
    def loop[F <: (A => Boolean)](i: Long, j: Long, f: F): F = Loop(this, i, j, f)
    def map[B](f: A => B): Vector[B] = Map(this, f)
    def offset(i: Long, j: Long): Vector[A] = Offset(this, i,j)
    def partition(p: A => Boolean): (Vector[A], Vector[A]) = Partition(this, p)
    def permutation(is: Vector[Long]): Vector[A] = Permutation(this, is)
    def readOnly: Vector[A] = ReadOnly(this)
    def remove(p: A => Boolean): Vector[A] = Remove(this, p)
    def replace(f: A => A): Vector[A] = Replace(this, f)
    def reverse: Vector[A] = Reverse(this)
    def rotate(i: Long): Vector[A] = Rotate(this, i)
    def slice(n: Long, m: Long): Vector[A] = Slice(this, n, m)
    def slice(n: Long): Vector[A] = Slice(this, n)
    def sort(lt: (A, A) => Boolean): Vector[A] = Sort(this, lt)
    def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)
    def splitAt(i: Long): (Vector[A], Vector[A]) = SplitAt(this, i)
    def step(n: Long, m: Long): Vector[A] = Step(this, n, m)
    def take(n: Long): Vector[A] = Take(this, n)
    def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)
    def toArray: Array[A] = ToArray(this)
    def toCell: Cell[A] = ToCell(this)
    def toIterator: Iterator[A] = ToIterator(this)
    def toJclArrayList: java.util.ArrayList[A] = jcl.ToArrayList(this)
    def toOption: Option[A] = ToOption(this)
    def toRandomAccessSeq: RandomAccessSeq.Mutable[A] = ToRandomAccessSeq(this)
    def window(n: Long, m: Long): Vector[A] = Window(this, n, m)
    def writer(i: Long): (A => Unit) = Writer(this, i)
    def ++(that: Vector[A]): Vector[A] = append(that)
}
