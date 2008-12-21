

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Vector {
    type NotReadableError[A] = vec.NotReadableError[A]
    type NotWritableError[A] = vec.NotWritableError[A]

    type Adapter[Z, A] = vec.Adapter[Z, A]
    type NotWritable[A] = vec.NotWritable[A]

    type Into[A] = vec.Into[A]

    def empty[A]: Vector[A] = new vec.EmptyVector[A]
    def fromArray[A](u: Array[A]): Vector[A] = new vec.ArrayVector(u)
    def fromCell[A](u: Cell[A]): Vector[A] = new vec.CellVector(u)
    def fromIterator[A](u: Iterator[A]): Vector[A] = new vec.IteratorVector(u)
    def fromJclArrayList[A](u: java.util.ArrayList[A]): Vector[A] = new vec.jcl.ArrayListVector(u)
    def fromRandomAccessSeq[A](u: RandomAccessSeq[A]): Vector[A] = vec.FromRandomAccessSeq(u)
    def fromString(u: String): Vector[Char] = new vec.StringVector(u)
    def fromValues[A](es: A*): Vector[A] = fromJclArrayList(vec.jcl.NewArrayList(es: _*))
    def single[A](u: A): Vector[A] = new vec.SingleVector(u)
    def range(i: Int, j: Int): Vector[Int] = new vec.IntRangeVector(i, j)
    def range(i: Long, j: Long): Vector[Long] = new vec.LongRangeVector(i, j)
    def stringize(v: Vector[Char]): String = vec.Stringize(v)
}


trait Vector[A] {
    import vec._

    def size: Long
    def apply(i: Long): A = throw new NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new NotWritableError(this)

    final def vector: Vector[A] = this
    final def toPair: (Long, Long) = (0, size)
    final def toTriple: (Vector[A], Long, Long) = (this, 0, size)

    override def equals(that: Any): Boolean = that match {
        case that: Vector[_] => equalsWith(this)(stl.EqualTo)
        case _ => false
    }

    def always[B](that: Vector[B]): Vector[B] = that
    def asVectorOf[B]: Vector[B] = new AsVectorOfVector[A, B](this)
    def append(that: Vector[A]): Vector[A] = new AppendVector(this, that) // kernel
    def bounds: Vector[A] = new BoundsVector(this)
    def contains(e: Any): Boolean = exists(_ == e)
    def copy: Vector[A] = force.cut
    def cut: Vector[A] = new CutVector(this) // kernel
    def cycle(n: Long): Vector[A] = new CycleVector(this, n) // kernel
    def drop(n: Long): Vector[A] = window(Math.min(n, size), size)
    def dropWhile(p: A => Boolean): Vector[A] = window(stlFindIf(!p(_: A)), size)
    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(this, that, p) // kernel
    def empty: Vector[A] = window(0, 0)
    def exists(p: A => Boolean): Boolean = find(p) != None
    def filter(p: A => Boolean): Vector[A] = new FilterVector(this, p) // kernel
    def filtering(p: A => Boolean): Vector[A] = new FilteringVector(this, p) // kernel
    def find(p: A => Boolean): Option[A] = Find(this, p)
    def first: A = First(this)
    def forall(p: A => Boolean): Boolean = find(!p(_: A)) == None
    def force: Vector[A] = Vector.fromArray(toArray)
    def foldLeft[B](z: B, op: (B, A) => B): B = stlAccumulate(z, op)
    def foldRight[B](z: B, op: (A, B) => B): B = reverse.foldLeft(z, { (b: B, a: A) => op(a, b) })
    def foreach(f: A => Unit): Unit = { stlForEach(f); () }
    def identity: Vector[A] = this
    def isDefinedAt(x: Long): Boolean = (x >= 0) && (x < size)
    def isEmpty: Boolean = size == 0
    def last: A = Last(this)
    def lazy_ : Vector[A] = new LazyVector(this) // kernel
    def length: Long = size
    def loop[F <: (A => Boolean)](f: F): F = Loop(this, f) // kernel
    def map[B](f: A => B): Vector[B] = new MapVector(this, f) // kernel
    def offset(i: Long, j: Long): Vector[A] = window(i, size + j)
    def partition(p: A => Boolean): (Vector[A], Vector[A]) = (filter(p), remove(p))
    def readOnly: Vector[A] = new ReadOnlyVector(this)
    def remove(p: A => Boolean): Vector[A] = filter(!p(_: A))
    def removing(p: A => Boolean): Vector[A] = filtering(!p(_: A))
    def reverse: Vector[A] = new ReverseVector(this) // kernel
    def rotate(i: Long): Vector[A] = window(i, size) ++ window(0, i)
    def slice(from: Long, until: Long): Vector[A] = drop(from).take(until - from)
    def slice(from: Long): Vector[A] = slice(from, size)
    def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)
    def splitAt(i: Long): (Vector[A], Vector[A]) = (window(0, i), window(i, size))
    def step(n: Long): Vector[A] = new StepVector(this, n)
    def swap(i: Long, j: Long): Unit = Swap(this, i, j) // kernel
    def take(n: Long): Vector[A] = window(0, Math.min(n, size))
    def takeWhile(p: A => Boolean): Vector[A] = window(0, stlFindIf(!p(_: A)))
    def toArray: Array[A] = ToArray(this)
    def toCell: Cell[A] = ToCell(this)
    def toIterator: Iterator[A] = new VectorIterator(this)
    def toJclArrayList: java.util.ArrayList[A] = jcl.ToArrayList(this)
    def toRandomAccessSeq: RandomAccessSeq.Mutable[A] = new VectorRandomAccessSeq(this)
    def window(n: Long, m: Long): Vector[A] = new WindowVector(this, n, m) // kernel
    def ++(that: Vector[A]): Vector[A] = append(that)

    def stlAccumulate[B](z: B, op: (B, A) => B): B = stl.Accumulate(this, z, op) // kernel
    def stlCopy[F <: (A => Any)](f: F): F = stlForEach(f)
    def stlCopyIf[F <: (A => Any)](f: F, p: A => Boolean): F = stl.CopyIf(this, f, p)
    def stlCopyBackward[B >: A](that: Vector[B], i: Long): Long = stl.CopyBackward(this, that, i)
    def stlCount(e: A): Long = stlCountIf(_ == e)
    def stlCountIf(p: A => Boolean): Long = stl.CountIf(this, p)
    def stlDistance: Long = size
    def stlEqual[B](that: Vector[B], i: Long): Boolean = stlEqual(that, i, stl.EqualTo)
    def stlEqual[B](that: Vector[B], i: Long, p: (A, B) => Boolean): Boolean = stl.Equal(this, that, i, p)
    def stlFind(e: A): Long = stlFindIf(_ == e)
    def stlFindIf(p: A => Boolean): Long = stl.FindIf(this, p) // kernel
    def stlForEach[F <: (A => Any)](f: F): F = stl.ForEach(this, f)
    def stlRemoveCopy[F <: (A => Any)](f: F, e: A): F = stlRemoveCopyIf(f, _ == e)
    def stlRemoveCopyIf[F <: (A => Any)](f: F, p: A => Boolean): F = stl.RemoveCopyIf(this, f, p)
    def stlRemove(e: A): Long = stlRemoveIf(_ == e)
    def stlRemoveIf(p: A => Boolean): Long = stl.RemoveIf(this, p)
    def stlReverse: Unit = stl.Reverse(this) // kernel

    def into(i: Long): Into[A] = new Into(this, i)
    def intoBegin: Into[A] = into(0)
    def intoEnd: Into[A] = into(size)
}
