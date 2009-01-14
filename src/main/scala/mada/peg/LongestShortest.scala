

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Longest {
    def apply[A](ps: Peg[A]*): Longest[A] = apply(ps.elements)

    def apply[A](ps: Iterator[Peg[A]]): Longest[A] = {
        val l = new Longest[A]
        for (p <- ps) {
            l.add(p)
        }
        l
    }
}

class Longest[A] extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = FAILURE
        for (p <- set) {
            val i = p.parse(v, first, last)
            if (i != FAILURE) {
                cur = Math.max(cur, i)
            }
        }
        cur
    }

    def add(p: Peg[A]): Boolean = set.add(p)
    def clear: Unit = set.clear
    def contains(p: Peg[A]): Boolean = set.contains(p)
    def isEmpty: Boolean = set.isEmpty
    def remove(p: Peg[A]): Boolean = set.remove(p)

    private val set = new scala.collection.jcl.HashSet[Peg[A]]
}


object Shortest {
    def apply[A](ps: Peg[A]*): Shortest[A] = apply(ps.elements)

    def apply[A](ps: Iterator[Peg[A]]): Shortest[A] = {
        val s = new Shortest[A]
        for (p <- ps) {
            s.add(p)
        }
        s
    }
}

class Shortest[A] extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = FAILURE
        for (p <- set) {
            val i = p.parse(v, first, last)
            if (i != FAILURE) {
                cur = Math.min(cur, i)
            }
        }
        cur
    }

    def add(p: Peg[A]): Boolean = set.add(p)
    def clear: Unit = set.clear
    def contains(p: Peg[A]): Boolean = set.contains(p)
    def isEmpty: Boolean = set.isEmpty
    def remove(p: Peg[A]): Boolean = set.remove(p)

    private val set = new scala.collection.jcl.HashSet[Peg[A]]
}
