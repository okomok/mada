

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Repeat {
    def apply[A](p: Peg[A], n: Int, m: Int): Peg[A] = {
        if (n < 0 || n > m) {
            throw new IllegalArgumentException("repeat" + (n, m))
        }
        new RepeatPeg(p, n, m)
    }
}

private[mada] class RepeatPeg[A](p: Peg[A], n: Int, m: Int) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        var cur = start
        var i = 0
        while (i < m) {
            val next = p.parse(v, cur, end)
            if (next == Peg.FAILURE) {
                return if (i < n) Peg.FAILURE else cur
            }
            cur = next
            i += 1
        }
        cur
    }
}
