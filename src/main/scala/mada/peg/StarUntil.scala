

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object StarUntil {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new StarUntilPeg(p, q)
}

private[mada] class StarUntilPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        var cur = start

        var next = q.parse(v, cur, end)
        while (next == Pegs.FAILURE) {
            next = p.parse(v, cur, end)
            if (next == Pegs.FAILURE) {
                return Pegs.FAILURE
            } else {
                cur = next
                next = q.parse(v, cur, end)
            }
        }

        next
    }
}
