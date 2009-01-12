

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SymbolSet {
    def apply[A](vs: Vector[A]*)(c: A => Ordered[A]): Peg[A] = new SymbolSet(vs.elements, vec.stl.Less(c))
    def apply[A](vs: Iterator[Vector[A]], lt: (A, A) => Boolean): Peg[A] = new SymbolSet(vs, lt)
}

class SymbolSet[A](vs: Iterator[Vector[A]], lt: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        tree.parse(v, first, last) match {
            case Some((_, cur)) => cur
            case _ => FAILURE
        }
    }

//    def add(v: Vector[A]): Option[Vector[A]] = tree.put(v, ())
    def clear: Unit = { }
    def contains(v: Vector[A]): Boolean = tree.containsKey(v)
    def isEmpty: Boolean = tree.isEmpty
//    def remove(v: Vector[A]): Boolean = { }

    private val tree = {
        val t = new TSTree[A, Unit](lt)
        for (v <- vs) {
            t.put(v, ())
        }
        t
    }
}
