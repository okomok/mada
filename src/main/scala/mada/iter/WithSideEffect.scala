

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object WithSideEffect {
    def apply[A](it: Iterator[A])(f: A => Unit): Iterator[A] = new WithSideEffectIterator(it, f)
}

private[mada] class WithSideEffectIterator[A](it: Iterator[A], f: A => Unit) extends Iterator[A] {
    override def hasNext = it.hasNext
    override def next = {
        val e = it.next
        f(e)
        e
    }
}
