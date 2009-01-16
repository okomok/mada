

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import scala.collection.Map


object Switch {
    def apply[A](es: Seq[(A, Peg[A])]): Peg[A] = {
        val m = new scala.collection.jcl.HashMap[A, Peg[A]]
        for (e <- es) {
            m.put(e._1, e._2)
        }
        apply(m)
    }

    def apply[A](es: Map[A, Peg[A]]): Peg[A] = new SwitchPeg(es)
}

class SwitchPeg[A](es: Map[A, Peg[A]]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = {
        if (first == last) {
            FAILURE
        } else {
            val p = es.get(v(first))
            if (p.isEmpty) {
                FAILURE
            } else {
                p.get.parse(v, first + 1, last)
            }
        }
    }
}
