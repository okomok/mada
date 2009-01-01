

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Single {
    def apply[A](e: A): Parser[A] = new SingleParser(e)
}

class SingleParser[A](e: A) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (first == last || e != s(first)) {
            FAILED
        } else {
            first + 1
        }
    }

    override def length = 1
}
