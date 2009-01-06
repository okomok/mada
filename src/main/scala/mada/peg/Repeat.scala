

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Repeat {
    def apply[A](p: Peg[A], min: Long, max: Long): Peg[A] = {
        Assert(0 <= min)
        Assert(min <= max)
        new RepeatPeg(p, min, max)
    }
}

class RepeatPeg[A](p: Peg[A], min: Long, max: Long) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = first

        var i = 0L
        while (i < max) {
            cur = p.parse(v, cur, last)
            if (cur == FAILED) {
                if (i < min) // not enough
                    return FAILED
                else
                    return cur
            }
            i += 1
        }

        cur
    }
}
