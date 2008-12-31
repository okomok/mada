

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Matches {
    def apply[A](p: Parser[A], v: Vector[A]): Boolean = {
        val (first, last) = v.toPair
        p.parse(Scanner.default(v), first, last) == last
    }
}
