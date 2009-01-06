

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object StarUntil {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new StarUntilPeg(p, q)
}

class StarUntilPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = first

        var next = q.parse(v, cur, last)
        while (next == FAILED) {
            next = p.parse(v, cur, last)
            if (next == FAILED) {
                return FAILED
            } else {
                cur = next
                next = q.parse(v, cur, last)
            }
        }

        next
    }
}
