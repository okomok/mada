

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Repeat {
    def apply[A](p: Peg[A], min: Int, max: Int): Peg[A] = {
        Assert(0 <= min)
        Assert(min <= max)
        new RepeatPeg(p, min, max)
    }
}

class RepeatPeg[A](p: Peg[A], min: Int, max: Int) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        var cur = start

        var i = 0
        while (i < max) {
            cur = p.parse(v, cur, end)
            if (cur == Peg.FAILURE) {
                if (i < min) // not enough
                    return Peg.FAILURE
                else
                    return cur
            }
            i += 1
        }

        cur
    }
}
