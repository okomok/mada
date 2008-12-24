

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FromStream {
    def apply[A](u: Stream[A]): Vector[A] = new StreamVector(u)
}

class StreamVector[A](s: Stream[A]) extends Adapter[A, A] with NotWritable[A] {
    override val * = {
        val a = new java.util.ArrayList[A]
        s.foreach(a.add(_: A))
        Vector.fromJclArrayList(a)
    }

    override def force = this
    override def toStream = s
}


object ToStream {
    def apply[A](v: Vector[A]): Stream[A] = Stream.fromIterator(v.toIterator)
}
