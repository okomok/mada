

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Minus {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new MinusParser(p, q)
}

class MinusParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val pcur = p.parse(v, first, last)
        if (pcur == FAILED) {
            FAILED
        } else {
            val qcur = q.parse(v, first, last)
            if (qcur == FAILED || qcur < pcur) {
                pcur
            } else {
                FAILED
            }
        }
    }

    override def length = p.length
}
