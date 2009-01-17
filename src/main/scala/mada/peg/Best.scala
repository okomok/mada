

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Longest {
    def apply[A](ps: Peg[A]*): Peg[A] = apply(ps)
    def apply[A](ps: Iterable[Peg[A]]): Peg[A] = new BestPeg(ps, Math.max)
}

object Shortest {
    def apply[A](ps: Peg[A]*): Peg[A] = apply(ps)
    def apply[A](ps: Iterable[Peg[A]]): Peg[A] = new BestPeg(ps, Math.min)
}


class BestPeg[A](ps: Iterable[Peg[A]], which: (Long, Long) => Long) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = {
        var cur = FAILURE
        for (p <- ps.projection) {
            val i = p.parse(v, first, last)
            if (i != FAILURE) {
                cur = which(cur, i)
            }
        }
        cur
    }
}
