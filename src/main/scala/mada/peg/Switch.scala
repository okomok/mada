

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Switch {
    def apply[A](es: (A, Peg[A])*): Switch[A] = apply(es.elements)

    def apply[A](es: Iterator[(A, Peg[A])]): Switch[A] = {
        val s = new Switch[A]
        for (e <- es) {
            s.put(e._1, e._2)
        }
        s
    }
}

class Switch[A] extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (first == last) {
            FAILURE
        } else {
            val p = table.get(v(first))
            if (p.isEmpty) {
                FAILURE
            } else {
                p.get.parse(v, first + 1, last)
            }
        }
    }

    def clear: Unit = table.clear
    def containsKey(key: A): Boolean = table.contains(key)
    def get(key: A): Option[Peg[A]] = table.get(key)
    def isEmpty: Boolean = table.isEmpty
    def put(key: A, value: Peg[A]): Option[Peg[A]] = table.put(key, value)
    def remove(key: A): Option[Peg[A]] = table.removeKey(key)

    private val table = new scala.collection.jcl.HashMap[A, Peg[A]]
}
