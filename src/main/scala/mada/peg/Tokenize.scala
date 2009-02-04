

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Tokenize {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[Vector[A]] = new TokenizeIterator(p, v)
}

private[mada] class TokenizeIterator[A](p: Peg[A], v: Vector[A]) extends Iterator[Vector[A]] {
    private var (k, l) = Find.impl(p, v, v.start, v.end)
    override def hasNext = l != Pegs.FAILURE
    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val tmp = new Vectors.Region(v, k, l)
        k_l(Find.impl(p, v, l, v.end))
        tmp
    }

    private def k_l(r: (Int, Int)): Unit = {
        k = r._1; l = r._2
    }
}
