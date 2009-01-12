

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SymbolSet {
    def apply[A](vs: Vector[A]*)(implicit c: A => Ordered[A]): Peg[A] = apply(vs.elements, vec.stl.Less(c))

    def apply[A](vs: Iterator[Vector[A]], lt: (A, A) => Boolean): Peg[A] = {
        val set = new SymbolSet(lt)
        for (v <- vs) {
            set.add(v)
        }
        set
    }
}

class SymbolSet[A](lt: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        tree.parse(v, first, last) match {
            case Some((_, cur)) => cur
            case _ => FAILURE
        }
    }

    def add(v: Vector[A]): Boolean = tree.put(v, ()).isEmpty
    def clear: Unit = tree.clear
    def contains(v: Vector[A]): Boolean = tree.containsKey(v)
    def isEmpty: Boolean = tree.isEmpty
    def remove(v: Vector[A]): Boolean = !tree.remove(v).isEmpty

    private val tree = new TSTree[A, Unit](lt)
}
