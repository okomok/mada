

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object LookBack {
    def apply[A](p: Parser[A]): Parser[A] = new LookBackParser(p)
}

class LookBackParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val (w, i, j) = v.window(0, first).reverse.toTriple
        if (p.parse(w, i, j) == FAILED) {
            FAILED
        } else {
            first
        }
    }

    override def length = 0
}
