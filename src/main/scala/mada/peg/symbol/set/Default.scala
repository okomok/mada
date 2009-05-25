

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.symbol.set


case class Default[A](_1: compare.Func[A]) extends Set[A] {

    private val tree = new TSTree[A, Unit](_1)


// Peg

    override def parse(v: Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((_, cur)) => cur
            case _ => FAILURE
        }
    }


// scala.collection.Set

    override def elements = new Iterator[Vector[A]] {
        private val it = tree.elements
        override def hasNext = it.hasNext
        override def next = it.next._1
    }

    override def contains(v: Vector[A]) = tree.containsKey(v)
    override def hashCode = tree.hashCode
    override def isEmpty = tree.isEmpty
    override def size = tree.size
    override def stringPrefix = "SymbolSet"

    override def +=(v: Vector[A]) = { tree.put(v, ()); this }
    override def -=(v: Vector[A]) = { tree.remove(v); this }
    override def clear = tree.clear
    //override def clone: Set[A] = new Default(tree.clone)

}
