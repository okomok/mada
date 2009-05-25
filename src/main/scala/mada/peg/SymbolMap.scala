

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] class TheSymbolMap[A](tree: TSTree[A, Peg[A]]) extends Peg[A] with scala.collection.mutable.Map[Vector[A], Peg[A]] {

// Peg
    override def parse(v: Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((p, cur)) => p.parse(v, cur, end)
            case _ => FAILURE
        }
    }

// scala.colletion.Map
    override def elements = tree.elements.map{ n => (n._1, n._2) }

    override def get(key: Vector[A]) = tree.get(key)
    override def hashCode = tree.hashCode
    override def isEmpty = tree.isEmpty
    override def size = tree.size
    override def stringPrefix = "SymbolMap"

    override def +=(kv: (Vector[A], Peg[A])) = { tree.put(kv._1, kv._2); this }
    override def -=(key: Vector[A]) = { tree.remove(key); this }
    override def clear = tree.clear
    override def clone: SymbolMap[A] = new TheSymbolMap(tree.clone)

}
