

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Split {
    def apply[A](p: Peg[A], v: Vector[A]): Iterable[Vector[A]] = Iterables.byName(iimpl(p, v))
    def iimpl[A](p: Peg[A], v: Vector[A]): Iterator[Vector[A]] = new SplitIterator(p, v)
}

private[mada] class SplitIterator[A](p: Peg[A], v: Vector[A]) extends Iterator[Vector[A]] {
    private val u = new RepeatAtMostUntilPeg(Peg.any, Math.MAX_INT, Peg.end | p)
    private var (k, b, l) = u.parseImpl(v, v.start, v.end)

    override def hasNext = k != l
    override def next = {
        Iterables.nextPrecondition(this, "split")
        val tmp = new Vector.Region(v, k, b)
        k_b_l_assign(u.parseImpl(v, l, v.end))
        tmp
    }

    private def k_b_l_assign(r: (Int, Int, Int)): Unit = {
        k = r._1; b = r._2; l = r._3;
    }
}
