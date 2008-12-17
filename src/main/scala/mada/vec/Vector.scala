

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Vector {
    trait Adapter[A, B] extends Vector[B] {
        def * : Vector[A]
        override def apply(i: Long): B = *(i).asInstanceOf[B]
        override def update(i: Long, e: B): Unit = *(i) = e.asInstanceOf[A]
        override def size = *.size
    }

    trait NotWritable[A] extends Vector[A] {
        override def update(i: Long, e: A): Unit = throw new NotWritableError(this)
    }

    class NotReadableError[A](val vector: Vector[A]) extends Error
    class NotWritableError[A](val vector: Vector[A]) extends Error

    final class Pointer[A](val vector: Vector[A], private var i: Long) {
        def index: Long = i
        def read: A = vector(i)
        def write(e: A): Unit = vector(i) = e
        def +=(d: Long) = i += d
        def -=(d: Long) = i -= d
    }
}


trait Vector[A] extends Expr.Start[Vector[A]] {
    def apply(i: Long): A = throw new Vector.NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new Vector.NotWritableError(this)
    def size: Long

    final def vector = this
    final def toRange = (0L, size)
    final def toTriple = (this, 0L, size)

    final def toPointer(i: Long) = new Vector.Pointer(this, i)
    final def begin = toPointer(0)
    final def end = toPointer(size)
    final def update[B >: A](p: Vector.Pointer[B], e: A): Unit = p.write(e)
}
