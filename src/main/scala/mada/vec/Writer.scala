

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Writer {
    def apply[A](v: Vector[A], i: Long): (A => Unit) = new WriterFunction(v, i)
}

class WriterFunction[A](* : Vector[A], start: Long) extends (A => Unit) {
    private var first = start
    override def apply(e: A): Unit = { *(first) = e; first += 1 }
}
