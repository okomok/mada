

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Star {
    def apply[A](p: Peg[A]): Peg[A] = new StarPeg(p)
}

class StarPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = first

        while (true) {
            val next = p.parse(v, cur, last)
            if (next == FAILURE) {
                return cur
            } else if (next == last) {
                return last
            } else {
                // Assert("StarPeg must advance; `end/eol` are the usual suspects", cur != next) // dynamic parsers can't be assert.
                cur = next
            }
        }

        cur
    }
}
