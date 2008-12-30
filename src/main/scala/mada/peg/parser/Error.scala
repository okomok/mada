

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Error {
    def apply[A]: Parser[A] = new ErrorParser[A]
}

class ErrorParser[A] extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        throw new Error("ErrorParser.parse")
    }
}
