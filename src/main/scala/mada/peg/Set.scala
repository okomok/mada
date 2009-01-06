

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Set {
    def apply[A](es: A*): Peg[A] = new SetPeg(es: _*)
}

class SetPeg[A](es: A*) extends Peg[A] {
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
