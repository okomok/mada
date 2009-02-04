

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Star {
    def apply[A](p: Peg[A]): Peg[A] = new StarPeg(p)
}

private[mada] class StarPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        var cur = start

        while (true) {
            val next = p.parse(v, cur, end)
            if (next == Pegs.FAILURE) {
                return cur
            } else if (next == end) {
                return end
            } else {
                // Assert("StarPeg must advance; `end/eol` are the usual suspects", cur != next) // dynamic parsers can't be assert.
                cur = next
            }
        }

        cur
    }
}
