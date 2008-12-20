

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Vector {
    type NotReadableError[A] = vec2.NotReadableError[A]
    type NotWritableError[A] = vec2.NotWritableError[A]

    type Adapter[Z, A] = vec2.Adapter[Z, A]
    type NotWritable[A] = vec2.NotWritable[A]

    type Into[A] = vec2.Into[A]

    def fromArray[A](u: Array[A]): Vector[A] = new vec2.ArrayVector(u)
    def fromJclArrayList[A](u: java.util.ArrayList[A]): Vector[A] = new vec2.jcl.ArrayListVector(u)
    def fromString(u: String): Vector[Char] = new vec2.StringVector(u)

    def stringize(v: Vector[Char]): String = vec2.Stringize(v)
}


trait Vector[A] {
    import vec2._

    def size: Long
    def apply(i: Long): A = throw new NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new NotWritableError(this)

    final def vector: Vector[A] = this
    final def toPair: (Long, Long) = (0, size)
    final def toTriple: (Vector[A], Long, Long) = (this, 0, size)

    override def equals(that: Any): Boolean = that match {
        case that: Vector[_] => Equals(this, that)
        case _ => false
    }

    def always[B](that: Vector[B]): Vector[B] = that
    def append(that: Vector[A]): Vector[A] = new AppendVector(this, that)
    def contains(elem: Any): Boolean = exists(_ == elem)
    def copy: Vector[A] = force.cut
    def cut: Vector[A] = new CutVector(this)
    def cycle(n: Long): Vector[A] = new CycleVector(this, n)
    def drop(n: Long): Vector[A] = window(Math.min(n, size), size)
    def dropWhile(p: A => Boolean): Vector[A] = window(stlFindIf(!p(_: A)), size)
    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = Equals(this, that, p)
    def exists(p: A => Boolean): Boolean = find(p) != None
    def filter(p: A => Boolean): Vector[A] = new FilterVector(this, p)
    def filtering(p: A => Boolean): Vector[A] = new FilteringVector(this, p)
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
    def lazy_ : Vector[A] = new LazyVector(this)
    def last: A = Last(this)
    def length: Long = size
    def loop[F <: (A => Boolean)](f: F): F = Loop(this, f)
    def map[B](f: A => B): Vector[B] = new MapVector(this, f)
    def offset(i: Long, j: Long): Vector[A] = window(i, size + j)
    def remove(p: A => Boolean): Vector[A] = filter(!p(_: A))
    def removing(p: A => Boolean): Vector[A] = filtering(!p(_: A))
    def reverse: Vector[A] = new ReverseVector(this)
    def slice(from: Long, until: Long): Vector[A] = drop(from).take(until - from)
    def slice(from: Long): Vector[A] = slice(from, size)
    def swap(i: Long, j: Long): Unit = Swap(this, i, j)
    def take(n: Long): Vector[A] = window(0, Math.min(n, size))
    def takeWhile(p: A => Boolean): Vector[A] = window(0, stlFindIf(!p(_: A)))
    def toArray: Array[A] = vec2.ToArray(this)
    def toJclArrayList: java.util.ArrayList[A] = jcl.ToArrayList(this)
    def window(n: Long, m: Long): Vector[A] = new WindowVector(this, n, m)
    def ++(that: Vector[A]): Vector[A] = append(that)

    def stlAccumulate[B](z: B, op: (B, A) => B): B = stl.Accumulate(this, z, op)
    def stlCopy[F <: (A => Any)](f: F): F = stlForEach(f)
    def stlCopyIf[F <: (A => Any)](f: F, p: A => Boolean): F = stl.CopyIf(this, f, p)
    def stlCopyBackward[B >: A](that: Vector[B]): Unit = stl.CopyBackward(this, that)
    def stlCount(e: A): Long = stlCountIf(_ == e)
    def stlCountIf(p: A => Boolean): Long = stl.CountIf(this, p)
    def stlDistance: Long = size
    def stlFind(e: A): Long = stlFindIf(_ == e)
    def stlFindIf(p: A => Boolean): Long = stl.FindIf(this, p)
    def stlForEach[F <: (A => Any)](f: F): F = stl.ForEach(this, f)
    def stlRemoveCopy[F <: (A => Any)](f: F, e: A): F = stlRemoveCopyIf(f, _ == e)
    def stlRemoveCopyIf[F <: (A => Any)](f: F, p: A => Boolean): F = stl.RemoveCopyIf(this, f, p)
    def stlRemove(e: A): Long = stlRemoveIf(_ == e)
    def stlRemoveIf(p: A => Boolean): Long = stl.RemoveIf(this, p)
    def stlReverse: Unit = stl.Reverse(this)

    def into(i: Long): Into[A] = new Into(this, i)
    def intoBegin: Into[A] = into(0)
    def intoEnd: Into[A] = into(size)
}
