

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Any_ {
    def apply[A]: Parser[A] = new AnyParser[A]
}

class AnyParser[A] extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (first == last) {
            FAILED
        } else {
            first + 1
        }
    }

    override def length = 1
}
