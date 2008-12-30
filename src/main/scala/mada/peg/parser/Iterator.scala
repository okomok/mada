

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object FromIterator {
    def apply[A1](it: Iterator[A1]): Parser[A1] = apply[A1, A1](it, vec.stl.EqualTo)
    def apply[A1, A2](it: Iterator[A1], pred: (A1, A2) => Boolean): Parser[A2] = new IteratorParser(it, pred)
}

class IteratorParser[A1, A2](it: Iterator[A1], pred: (A1, A2) => Boolean) extends Parser[A2] {
    override def parse(s: Scanner[A2], first: Long, last: Long): Long = {
        var cur = first

        while (cur != last && it.hasNext) {
            if (!pred(it.next, s(cur))) {
                return FAILED
            }
            cur += 1
        }

        if (it.hasNext) {
            FAILED
        } else {
            cur
        }
    }
}
