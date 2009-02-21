

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Longest {
    def apply[A](ps: Iterable[Peg[A]]): Peg[A] = new BestPeg(ps, Math.max)
}

private[mada] object Shortest {
    def apply[A](ps: Iterable[Peg[A]]): Peg[A] = new BestPeg(ps, Math.min)
}


private[mada] class BestPeg[A](ps: Iterable[Peg[A]], which: (Int, Int) => Int) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val curs = ps.elements.map{ p => p.parse(v, start, end) }.filter{ i => i != Peg.FAILURE }
        if (Iterators.isEmpty(curs)) Peg.FAILURE else Iterators.best(curs, which)
    }
}
