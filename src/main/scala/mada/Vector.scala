

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Vector {

    class NotReadableError[A](val vector: Vector[A]) extends Error
    class NotWritableError[A](val vector: Vector[A]) extends Error


    trait Adapter[A, B] extends Vector[B] {
        def * : Vector[A]
        override def size = *.size
        override def apply(i: Long): B = *(i).asInstanceOf[B]
        override def update(i: Long, e: B): Unit = *(i) = e.asInstanceOf[A]
    }

    trait NotWritable[A] extends Vector[A] {
        override def update(i: Long, e: A): Unit = throw new NotWritableError(this)
    }


    class Into[A](val vector: Vector[A], val start: Long) extends (A => Any) {
        private var i = start
        override def apply(e: A) = { vector(i) = e; i += 1 }
        final def index = i
    }


    def fromArray[A](a: Array[A]) = new vec2.ArrayVector(a).vector
    def fromJclArrayList[A](a: java.util.ArrayList[A]) = new vec2.jcl.ArrayListVector(a).vector

}


trait Vector[A] {

    def size: Long
    def apply(i: Long): A = throw new Vector.NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new Vector.NotWritableError(this)

    final def vector = this
    final def toPair = (0L, size)
    final def toTriple = (this, 0L, size)

    final def swap(i: Long, j: Long) = vec2.Swap(this, i, j)


    override def equals(that: Any) = that match {
        case that: Vector[_] => vec2.Equals(this, that)
        case _ => false
    }

    final def equalsIf[B](that: Vector[B], p: (A, B) => Boolean) = vec2.EqualsIf(this, that, p)

    final def into(i: Long) = new Vector.Into(this, i)
    final def intoBegin = into(0)
    final def intoEnd = into(size)

    def loop[F <: (A => Boolean)](f: F) = vec2.Loop(this, f)
    final def foreach(f: A => Unit) = { stlForEach(f); () }

    def window(n: Long, m: Long) = new vec2.WindowVector(this, n, m).vector
    final def drop(n: Long) = window(Math.min(n, size), size)
    final def take(n: Long) = window(0, Math.min(n, size))
    final def slice(n: Long, m: Long) = drop(n).take(m - n)

    def toArray = vec2.ToArray(this)
    def toJclArrayList = vec2.jcl.ToArrayList(this)
    final def force = Vector.fromArray(toArray)

    def map[B](f: A => B) = new vec2.MapVector(this, f).vector
    def filter(p: A => Boolean) = new vec2.FilterVector(this, p).vector
    final def remove(p: A => Boolean) = filter(!p(_: A))

    final def stlCopy[F <: (A => Any)](f: F) = stlForEach(f)
    final def stlCopyIf[F <: (A => Any)](f: F, p: A => Boolean) = vec2.stl.CopyIf(this, f, p)
    final def stlDistance = size
    final def stlFind(e: A) = stlFindIf(_ == e)
    final def stlFindIf(p: A => Boolean) = vec2.stl.FindIf(this, p)
    final def stlForEach[F <: (A => Any)](f: F) = vec2.stl.ForEach(this, f)
    final def stlRemoveCopy[F <: (A => Any)](f: F, e: A) = stlRemoveCopyIf(f, _ == e)
    final def stlRemoveCopyIf[F <: (A => Any)](f: F, p: A => Boolean) = vec2.stl.RemoveCopyIf(this, f, p)
    final def stlRemove(e: A) = stlRemoveIf(_ == e)
    final def stlRemoveIf(p: A => Boolean) = vec2.stl.RemoveIf(this, p)

}
