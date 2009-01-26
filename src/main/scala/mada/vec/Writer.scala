

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Writer {
    def apply[A](v: Vector[A], i: Int): (A => Unit) = new WriterFunction(v, i)
}

private[mada] class WriterFunction[A](* : Vector[A], start: Int) extends (A => Unit) {
    private var i = start
    override def apply(e: A): Unit = { *(i) = e; i += 1 }
}
