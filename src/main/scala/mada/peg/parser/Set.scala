

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Set {
    def apply[A](es: A*): Parser[A] = new SetParser(es: _*)
}

class SetParser[A](es: A*) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var it = es.elements
        while (it.hasNext) {
            if (s(first) == it.next) {
                return first + 1
            }
        }

        FAILED
    }
}
