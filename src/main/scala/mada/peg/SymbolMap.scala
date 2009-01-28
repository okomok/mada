

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SymbolMap {
    def apply[A](es: (Vector[A], Peg[A])*)(implicit c: A => Ordered[A]): SymbolMap[A] = apply(es.elements, Functions.less(c))

    def apply[A](es: Iterator[(Vector[A], Peg[A])], lt: (A, A) => Boolean): SymbolMap[A] = {
        val map = new SymbolMap(lt)
        for (e <- es) {
            map.put(e._1, e._2)
        }
        map
    }
}

class SymbolMap[A] private (private val tree: TSTree[A, Peg[A]]) extends Peg[A] with scala.collection.mutable.Map[Vector[A], Peg[A]] {
    def this(lt: (A, A) => Boolean) = this(new TSTree[A, Peg[A]](lt))

    override def parse(v: Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((p, cur)) => p.parse(v, cur, end)
            case _ => Peg.FAILURE
        }
    }

// Collection
    override def elements = new Iterator[(Vector[A], Peg[A])] {
        private val it = tree.elements
        override def hasNext = it.hasNext
        override def next = { val n = it.next; (n._1, n._2) }
    }

// Map
    override def get(key: Vector[A]) = tree.get(key)
    override def hashCode = tree.hashCode
    override def isEmpty = tree.isEmpty
    override def size = tree.size
    protected override def stringPrefix = "SymbolMap"

// mutable.Map
    override def -=(key: Vector[A]) = tree.remove(key)
    override def clear = tree.clear
    override def clone: SymbolMap[A] = new SymbolMap(tree.clone)
    override def update(key: Vector[A], value: Peg[A]) = tree.put(key, value)
}
