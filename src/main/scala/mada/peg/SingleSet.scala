

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SingleSet {
    def apply[A](es: A*): SingleSet[A] = apply(es.elements)

    def apply[A](es: Iterator[A]): SingleSet[A] = {
        val s = new SingleSet[A]
        for (e <- es) {
            s.add(e)
        }
        s
    }
}

class SingleSet[A] extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (first == last || !contains(v(first))) {
            FAILURE
        } else {
            first + 1
        }
    }
    override def length = 1

    def add(e: A): Boolean = set.add(e)
    def clear: Unit = set.clear
    def contains(e: A): Boolean = set.contains(e)
    def isEmpty: Boolean = set.isEmpty
    def remove(e: A): Boolean = set.remove(e)

    private val set = new scala.collection.jcl.HashSet[A]
}
