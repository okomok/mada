

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Vector {
    trait Adapter[A, B] extends Vector[B] {
        def * : Vector[A]
        override def size = *.size
        override def apply(i: Long): B = *(i).asInstanceOf[B]
        override def update(i: Long, e: B): Unit = *(i) = e.asInstanceOf[A]
    }

    trait NotWritable[A] extends Vector[A] {
        override def update(i: Long, e: A): Unit = throw new NotWritableError(this)
    }

    class NotReadableError[A](val vector: Vector[A]) extends Error
    class NotWritableError[A](val vector: Vector[A]) extends Error

    case class Into[A](vector: Vector[A], start: Long) extends (A => Any) {
        private var i = start
        override def apply(e: A) = { vector(i) = e; i += 1 }
        final def index = i
    }
}


trait Vector[A] extends Expr.Start[Vector[A]] {
    def size: Long
    def apply(i: Long): A = throw new Vector.NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new Vector.NotWritableError(this)

    final def vector = this
    final def toPair = (0L, size)
    final def toTriple = (this, 0L, size)

    final def swap(i: Long, j: Long): Unit = {
        val * = this
        val tmp = *(i)
        *(i) = *(j)
        *(j) = tmp
    }

    override def equals(that: Any) = that match {
        case that: Vector[_] => { import Equals._; /.vequals(that)./ }
        case _ => false
    }

    final def into(i: Long) = new Vector.Into(this, i)
    final def intoBegin = into(0)
    final def intoEnd = into(size)
}
