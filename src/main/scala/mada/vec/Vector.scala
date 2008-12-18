

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

    case class OutputImpltion[A](vector: Vector[A], start: Long) extends (A => Any) {
        private var i = start
        override def apply(e: A) = { vector(i) = e; i += 1 }
        final def index: Long = i
    }
}


trait Vector[A] extends Expr.Start[Vector[A]] {
    def size: Long
    def apply(i: Long): A = throw new Vector.NotReadableError(this)
    def update(i: Long, e: A): Unit = throw new Vector.NotWritableError(this)

    final def vector = this
    final def toRange = (0L, size)
    final def toTriple = (this, 0L, size)

    final def out(i: Long) = new Vector.OutputImpltion(this, i)
    final def outBegin = out(0)
    final def outEnd = out(size)
}
