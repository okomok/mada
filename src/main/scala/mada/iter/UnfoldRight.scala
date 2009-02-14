

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object UnfoldRight {
    def apply[A, B](z: A)(op: A => Option[(B, A)]): Iterator[B] = new UnfoldRightIterator(z, op)
}

private[mada] class UnfoldRightIterator[A, B](z: A, op: A => Option[(B, A)]) extends Iterator[B] {
    private var acc = op(z)
    override def hasNext = !acc.isEmpty
    override def next = {
        val tmp = acc.get
        acc = op(tmp._2)
        tmp._1
    }
}
