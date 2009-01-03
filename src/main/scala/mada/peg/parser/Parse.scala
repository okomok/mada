

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Parse {
    def apply[A](p: Parser[A], v: Vector[A]): Long = {
        val (first, last) = v.toPair
        p.parse(v, first, last)
    }
}
