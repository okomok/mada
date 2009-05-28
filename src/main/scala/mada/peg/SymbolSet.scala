

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] class TheSymbolSet[A](tree: TSTree[A, Unit]) extends Peg[A] with scala.collection.mutable.Set[Vector[A]] {

// Peg
    override def parse(v: Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((_, cur)) => cur
            case _ => FAILURE
        }
    }

// scala.collection.Set
    override def iterator = tree.iterator.map{ n => n._1 }

    override def contains(v: Vector[A]) = tree.containsKey(v)
    override def hashCode = tree.hashCode
    override def isEmpty = tree.isEmpty
    override def size = tree.size
    override def stringPrefix = "SymbolSet"

    override def +=(v: Vector[A]) = { tree.put(v, ()); this }
    override def -=(v: Vector[A]) = { tree.remove(v); this }
    override def clear = tree.clear
    override def clone: SymbolSet[A] = new TheSymbolSet(tree.clone)

}
