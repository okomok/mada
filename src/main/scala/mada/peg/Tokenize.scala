

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Tokenize {
    def apply[A](p: Peg[A], v: Vector[A]): Iterable[Vector[A]] = Iterables.byName(iimpl(p, v))
    def iimpl[A](p: Peg[A], v: Vector[A]): Iterator[Vector[A]] = new TokenizeIterator(p, v)
}

private[mada] class TokenizeIterator[A](p: Peg[A], v: Vector[A]) extends Iterator[Vector[A]] {
    private var (k, l) = Find.impl(p, v, v.start, v.end)

    override def hasNext = l != Peg.FAILURE
    override def next = {
        Iterables.nextPrecondition(this, "tokenize")
        val tmp = new Vector.Region(v, k, l)
        k_l_assign(Find.impl(p, v, l, v.end))
        tmp
    }

    private def k_l_assign(r: (Int, Int)): Unit = {
        k = r._1; l = r._2
    }
}
