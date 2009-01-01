

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Opt {
    def apply[A](p: Parser[A]): Parser[A] = p or Parser.eps[A]
}

object OptBefore {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = q.lookAhead or (p seqAnd q.lookAhead)
}

object OptUntil {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = q or (p seqAnd q)
}
