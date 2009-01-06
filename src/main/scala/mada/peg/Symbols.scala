

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Symbols {
    def apply[A](vs: Vector[A]*)(c: A => Ordered[A]): Peg[A] = new Symbols(vs.elements, vec.stl.Less(c))
    def apply[A](vs: Iterator[Vector[A]], lt: (A, A) => Boolean): Peg[A] = new Symbols(vs, lt)
}

class Symbols[A](vs: Iterator[Vector[A]], lt: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        tree.parse(v, first, last)
    }

    private val tree = {
        val t = new TSTree[A, Unit](lt)
        vs.foreach(t.put(_, ()))
        t
    }
}
