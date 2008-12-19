

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Vector {
    type NotReadableError[A] = vec2.NotReadableError[A]
    type NotWritableError[A] = vec2.NotWritableError[A]

    type Adapter[Z, A] = vec2.Adapter[Z, A]
    type NotWritable[A] = vec2.NotWritable[A]

    type Into[A] = vec2.Into[A]

    def fromArray[A](u: Array[A]) = new vec2.ArrayVector(u).vector
    def fromJclArrayList[A](u: java.util.ArrayList[A]) = new vec2.jcl.ArrayListVector(u).vector
    def fromString[A](u: String) = new vec2.StringVector(u).vector

    def stringize(v: Vector[Char]) = vec2.Stringize(v)
}


trait Vector[A] {
    import vec2._

    def size: Long
    def apply(i: Long): A = throw new NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new NotWritableError(this)

    final def vector = this
    final def toPair = (0L, size)
    final def toTriple = (this, 0L, size)

    override def equals(that: Any) = that match {
        case that: Vector[_] => Equals(this, that)
        case _ => false
    }

    def always[B](that: Vector[B]) = that
    def append(that: Vector[A]) = new AppendVector(this, that).vector
    def cycle(n: Long) = new CycleVector(this, n).vector
    def drop(n: Long) = window(Math.min(n, size), size)
    def dropWhile(p: A => Boolean) = window(stlFindIf(!p(_: A)), size)
    def equalsIf[B](that: Vector[B], p: (A, B) => Boolean) = EqualsIf(this, that, p)
    def exists(p: A => Boolean) = find(p) != None
    def filter(p: A => Boolean) = new FilterVector(this, p).vector
    def find(p: A => Boolean) = Find(this, p)
    def forall(p: A => Boolean) = find(!p(_: A)) == None
    def force = Vector.fromArray(toArray)
    def foldLeft[B](z: B, op: (B, A) => B) = stlAccumulate(z, op)
    def foldRight[B](z: B, op: (A, B) => B) = reverse.foldLeft(z, { (b: B, a: A) => op(a, b) })
    def foreach(f: A => Unit) = { stlForEach(f); () }
    def identity = this
    def isEmpty = size == 0
    def loop[F <: (A => Boolean)](f: F) = Loop(this, f)
    def map[B](f: A => B) = new MapVector(this, f).vector
    def offset(i: Long, j: Long) = window(i, size + j)
    def remove(p: A => Boolean) = filter(!p(_: A))
    def reverse = new ReverseVector(this).vector
    def slice(n: Long, m: Long) = drop(n).take(m - n)
    def swap(i: Long, j: Long) = Swap(this, i, j)
    def take(n: Long) = window(0, Math.min(n, size))
    def takeWhile(p: A => Boolean) = window(0, stlFindIf(!p(_: A)))
    def toArray = vec2.ToArray(this)
    def toJclArrayList = jcl.ToArrayList(this)
    def window(n: Long, m: Long) = new WindowVector(this, n, m).vector

    def stlAccumulate[B](z: B, op: (B, A) => B) = stl.Accumulate(this, z, op)
    def stlCopy[F <: (A => Any)](f: F) = stlForEach(f)
    def stlCopyIf[F <: (A => Any)](f: F, p: A => Boolean) = stl.CopyIf(this, f, p)
    def stlCopyBackward[B >: A](that: Vector[B]) = stl.CopyBackward(this, that)
    def stlDistance = size
    def stlFind(e: A) = stlFindIf(_ == e)
    def stlFindIf(p: A => Boolean) = stl.FindIf(this, p)
    def stlForEach[F <: (A => Any)](f: F) = stl.ForEach(this, f)
    def stlRemoveCopy[F <: (A => Any)](f: F, e: A) = stlRemoveCopyIf(f, _ == e)
    def stlRemoveCopyIf[F <: (A => Any)](f: F, p: A => Boolean) = stl.RemoveCopyIf(this, f, p)
    def stlRemove(e: A) = stlRemoveIf(_ == e)
    def stlRemoveIf(p: A => Boolean) = stl.RemoveIf(this, p)
    def stlReverse = stl.Reverse(this)

    def into(i: Long) = new Into(this, i)
    def intoBegin = into(0)
    def intoEnd = into(size)
}
