

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object StarBefore {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = p.starUntil(q.before)
}
