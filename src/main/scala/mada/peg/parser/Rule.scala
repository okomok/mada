

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


class Rule[A] extends Ref[Parser[A]](null) with Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        deref.parse(s, first, last)
    }
}
