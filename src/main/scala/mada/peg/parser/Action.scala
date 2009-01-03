

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Action {
    def apply[A](p: Parser[A], f: Vector[A] => Any): Parser[A] = apply(p, Vector.triplify(f))
    def apply[A](p: Parser[A], f: (Vector[A], Long, Long) => Any): Parser[A] = new ActionParser(p, f)
}

class ActionParser[A](override val self: Parser[A], f: (Vector[A], Long, Long) => Any) extends ParserProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val cur = self.parse(v, first, last)
        if (cur != FAILED) {
            f(v, first, last)
        }
        cur
    }
}
