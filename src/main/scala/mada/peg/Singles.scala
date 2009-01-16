

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import scala.collection.Set


object Singles {
    def apply[A](es: Seq[A]): Peg[A] = {
        val s = new scala.collection.jcl.HashSet[A]
        for (e <- es) {
            s.add(e)
        }
        apply(s)
    }

    def apply[A](es: Set[A]): Peg[A] = new SinglesPeg(es)
}

class SinglesPeg[A](es: Set[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = {
        if (first == last || !es.contains(v(first))) {
            FAILURE
        } else {
            first + 1
        }
    }
    override def length = 1
}
