

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SymbolMap {
    def apply[A](es: (Vector[A], Peg[A])*)(implicit c: A => Ordered[A]): Peg[A] = apply(es.elements, vec.stl.Less(c))

    def apply[A](es: Iterator[(Vector[A], Peg[A])], lt: (A, A) => Boolean): Peg[A] = {
        val map = new SymbolMap(lt)
        for (e <- es) {
            map.put(e._1, e._2)
        }
        map
    }
}

class SymbolMap[A](lt: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        tree.parse(v, first, last) match {
            case Some((p, cur)) => p.parse(v, cur, last)
            case _ => FAILURE
        }
    }

    def clear: Unit = tree.clear
    def containsKey(key: Vector[A]): Boolean = tree.containsKey(key)
    def get(key: Vector[A]): Option[Peg[A]] = tree.get(key)
    def isEmpty: Boolean = tree.isEmpty
    def put(key: Vector[A], value: Peg[A]): Option[Peg[A]] = tree.put(key, value)
    def remove(key: Vector[A]): Option[Peg[A]] = tree.remove(key)

    private val tree = new TSTree[A, Peg[A]](lt)
}
