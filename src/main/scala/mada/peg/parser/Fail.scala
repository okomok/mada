

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Fail {
    def apply[A]: Parser[A] = new FailParser[A]
}

class FailParser[A] extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = FAILED
}
