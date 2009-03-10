

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object FromIterable {
    def apply[A](from: Iterable[A]): Peg[A] = apply(from, Functions.equal)
    def apply[A](from: Iterable[A], pred: (A, A) => Boolean): Peg[A] = new IterablePeg(from, pred)
}

private[mada] class IterablePeg[A](from: Iterable[A], pred: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        val it = from.elements
        var cur = start

        while (it.hasNext && cur != end) {
            if (!pred(it.next, v(cur))) {
                return Peg.FAILURE
            }
            cur += 1
        }

        if (cur == end && it.hasNext) Peg.FAILURE else cur
    }

    override def width = Iterables.length(from)
}
