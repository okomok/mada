

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Switch {
    def apply[A](es: (Vector[A], Peg[A])*)(c: A => Ordered[A]): Peg[A] = new Switch(es, vec.stl.Less(c))
    def apply[A](es: Seq[(Vector[A], Peg[A])], lt: (A, A) => Boolean): Peg[A] = new Switch(es, lt)
}

class Switch[A](es: Seq[(Vector[A], Peg[A])], lt: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        tree.parse(v, first, last) match {
            case Some((p, cur)) => p.parse(v, cur, last)
            case _ => FAILURE
        }
    }

    private val tree = {
        val t = new TSTree[A, Peg[A]](lt)
        for (e <- es) {
            t.put(e._1, e._2)
        }
        t
    }
}
