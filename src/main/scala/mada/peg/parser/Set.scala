

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Set {
    def apply[A](es: A*): Parser[A] = new SetParser(es: _*)
}

class SetParser[A](es: A*) extends Parser[A] {
    private val set = {
        val hs = new java.util.HashSet[A]
        val it = es.elements
        while (it.hasNext) {
            hs.add(it.next)
        }
        hs
    }

    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (first == last || !set.contains(v(first))) {
            FAILED
        } else {
            first + 1
        }
    }

    override def length = 1
}
