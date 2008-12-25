

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
    def lefts[A, B](v: Vector[Either[A, B]]): Vector[A] = vec.Lefts(v)
    def rights[A, B](v: Vector[Either[A, B]]): Vector[B] = vec.Rights(v)
    def range(i: Int, j: Int): Vector[Int] = vec.IntRange(i, j)
    def range(i: Long, j: Long): Vector[Long] = vec.LongRange(i, j)
    def toString(v: Vector[Char]): String = vec.ToString(v)
    def untokenize[A](vv: Vector[Vector[A]], sep: Vector[A]): Vector[A] = vec.Untokenize(vv, sep)
    def unzip[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = vec.Unzip(v)

    type NotReadableError[A] = vec.NotReadableError[A]
    type NotWritableError[A] = vec.NotWritableError[A]

    type VectorAdapter[Z, A] = vec.VectorAdapter[Z, A]
    type VectorProxy[A] = vec.VectorProxy[A]
    type NotWritable[A] = vec.NotWritable[A]

    type IntFileVector = vec.IntFileVector
    type LongFileVector = vec.LongFileVector
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
    override def toString: String = AnyToString(this)

    final def always[B](that: Vector[B]): Vector[B] = Always(this, that)
    final def append(that: Vector[A]): Vector[A] = Append(this, that)
    final def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)
    def bounds: Vector[A] = Bounds(this)
    final def break(p: A => Boolean): (Vector[A], Vector[A]) = Break(this, p)
    final def clear: Vector[A] = Clear(this)
    final def contains(e: Any): Boolean = Contains(this, e)
    final def count(p: A => Boolean): Long = Count(this, p)
    def copy: Vector[A] = Copy(this)
    final def cut: Vector[A] = Cut(this)
    def cycle(n: Long): Vector[A] = Cycle(this, n)
    final def drop(n: Long): Vector[A] = Drop(this, n)
    final def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)
    final def elements: Iterator[A] = Elements(this)
    final def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(this, that, p)
    final def exists(p: A => Boolean): Boolean = Exists(this, p)
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)
    final def find(p: A => Boolean): Option[A] = Find(this, p)
    final def first: A = First(this)
    final def flatMap[B](f: A => Vector[B]): Vector[B] = FlatMap(this, f)
    final def forall(p: A => Boolean): Boolean = Forall(this, p)
    def force: Vector[A] = Force(this)
    final def foldLeft[B](z: B, op: (B, A) => B): B = FoldLeft(this, z, op)
    final def foldRight[B](z: B, op: (A, B) => B): B = FoldRight(this, z, op)
    final def foreach(f: A => Unit): Unit = Foreach(this, f)
    final def head: A = Head(this)
    final def identity: Vector[A] = this
    final def indices: Vector[Long] = Indices(this)
    final def init: Vector[A] = Init(this)
    final def isDefinedAt(x: Long): Boolean = IsDefinedAt(this, x)
    final def isEmpty: Boolean = IsEmpty(this)
    final def isNil: Boolean = IsNil(this)
    final def last: A = Last(this)
    def lazy_ : Vector[A] = Lazy(this)
    final def length: Long = size
    def loop[F <: (A => Boolean)](i: Long, j: Long, f: F): F = Loop(this, i, j, f)
    def map[B](f: A => B): Vector[B] = Map(this, f)
    final def offset(i: Long, j: Long): Vector[A] = Offset(this, i, j)
    final def partition(p: A => Boolean): (Vector[A], Vector[A]) = Partition(this, p)
    final def permutation(is: Vector[Long]): Vector[A] = Permutation(this, is)
    def readOnly: Vector[A] = ReadOnly(this)
    final def reduceLeft[B >: A](op: (B, A) => B): B = ReduceLeft(this, op)
    final def reduceRight[B >: A](op: (A, B) => B): B = ReduceRight(this, op)
    final def remove(p: A => Boolean): Vector[A] = Remove(this, p)
    def reverse: Vector[A] = Reverse(this)
    final def rotate(i: Long): Vector[A] = Rotate(this, i)
    final def slice(n: Long, m: Long): Vector[A] = Slice(this, n, m)
    final def slice(n: Long): Vector[A] = Slice(this, n)
    def sort(lt: (A, A) => Boolean): Vector[A] = Sort(this, lt)
    final def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)
    final def splitAt(i: Long): (Vector[A], Vector[A]) = SplitAt(this, i)
    def step(n: Long, m: Long): Vector[A] = Step(this, n, m)
    final def take(n: Long): Vector[A] = Take(this, n)
    final def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)
    final def tail: Vector[A] = Tail(this)
    def toArray: Array[A] = ToArray(this)
    def toCell: Cell[A] = ToCell(this)
    def toIterator: Iterator[A] = ToIterator(this)
    def toJclArrayList: java.util.ArrayList[A] = jcl.ToArrayList(this)
    def toOption: Option[A] = ToOption(this)
    def toRandomAccessSeq: RandomAccessSeq.Mutable[A] = ToRandomAccessSeq(this)
    def window(n: Long, m: Long): Vector[A] = Window(this, n, m)
    final def writer(i: Long): (A => Unit) = Writer(this, i)
    final def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)
    final def ++(that: Vector[A]): Vector[A] = append(that)
}
