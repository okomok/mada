

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Xor {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new XorParser(p, q)
}

class XorParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val pcur = p.parse(v, first, last)
        val qcur = q.parse(v, first, last)

        val pok = pcur != FAILED
        if (pok && qcur != FAILED) {
            FAILED
        } else if (pok) {
            pcur
        } else {
            qcur
        }
    }
}
