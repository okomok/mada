

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object And {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new AndParser(p, q)
}

class AndParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val pcur = p.parse(v, first, last)
        if (pcur != FAILED) {
            val qcur = q.parse(v, first, last) // short-circuit
            if (pcur == qcur) {
                pcur
            } else {
                FAILED
            }
        } else {
            FAILED
        }
    }

    override def length = p.length
}


object AndIf {
    def apply[A](p: Parser[A], pred: Vector[A] => Boolean): Parser[A] = apply(p, Vector.triplify(pred))
    def apply[A](p: Parser[A], pred: (Vector[A], Long, Long) => Boolean): Parser[A] = new AndIfParser(p, pred)
}

class AndIfParser[A](override val self: Parser[A], pred: (Vector[A], Long, Long) => Boolean) extends ParserProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val cur = self.parse(v, first, last)
        if (cur == FAILED || !pred(v, first, cur)) {
            FAILED
        } else {
            cur
        }
    }
}
