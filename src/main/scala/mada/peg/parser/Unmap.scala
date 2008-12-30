

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Unmap {
    def apply[Z, A](p: Parser[A], f: Z => A): Parser[Z] = new UnmapParser(p, f)
}

class UnmapParser[Z, A](p: Parser[A], f: Z => A) extends Parser[Z] {
    override def parse(s: Scanner[Z], first: Long, last: Long): Long = {
        p.parse(s.copy(s.map(f)), first, last)
    }
}
