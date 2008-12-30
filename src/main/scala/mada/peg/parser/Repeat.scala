

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Repeat {
    def apply[A](p: Parser[A], min: Long, max: Long): Parser[A] = {
        Assert(0 <= min)
        Assert(min <= max)
        new RepeatParser(p, min, max)
    }
}

class RepeatParser[A](p: Parser[A], min: Long, max: Long) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var cur = first

        var i = 0L
        while (i < max) {
            cur = p.parse(s, cur, last)
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
