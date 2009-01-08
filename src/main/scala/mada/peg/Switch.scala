

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Switch {
    def apply[A](es: (A, Peg[A])*): Peg[A] = new Switch(es)
}

class Switch[A](es: Seq[(A, Peg[A])]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (first == last) {
            FAILURE
        } else {
            val p = table.get(v(first))
            if (p.isEmpty) {
                FAILURE
            } else {
                val cur = p.get.parse(v, first, last) // from first.
                if (cur == FAILURE) {
                    0
                } else {
                    cur
                }
            }
        }
    }

    private val table = {
        val t = new scala.collection.jcl.HashMap[A, Peg[A]]
        for (e <- es) {
            t.put(e._1, e._2)
        }
        t
    }
}
