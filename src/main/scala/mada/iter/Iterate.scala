

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


// unfoldRight(z)({ x => Some(x, op(x)) }) always needs heap-allocation of Option.

private[mada] object Iterate {
    def apply[A](z: A)(op: A => A): Iterator[A] = new IterateIterator(z, op)
}

private[mada] class IterateIterator[A](z: A, op: A => A) extends Iterator[A] {
    private var acc = z
    override def hasNext = true
    override def next = {
        val tmp = acc
        acc = op(acc)
        tmp
    }
}
