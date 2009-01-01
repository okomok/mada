

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Prescan {
    def apply[Z, A](p: Parser[A], f: Vector[Z] => Vector[A]): Parser[Z] = new PrescanParser(p, f)
}

class PrescanParser[Z, A](p: Parser[A], f: Vector[Z] => Vector[A]) extends Parser[Z] {
    override def parse(s: Scanner[Z], first: Long, last: Long): Long = {
        p.parse(s.copy(f(s)), first, last) // f must return one-to-one view of Vector
    }

    override def length = p.length
}
