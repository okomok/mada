

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.symbol.map


case class Default[A](_1: compare.Func[A]) extends Map[A] {

    private val tree = new TSTree[A, Peg[A]](_1)


// Peg

    override def parse(v: Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((p, cur)) => p.parse(v, cur, end)
            case _ => FAILURE
        }
    }


// scala.colletion.Map

    override def elements = new scala.Iterator[(Vector[A], Peg[A])] {
        private val it = tree.elements
        override def hasNext = it.hasNext
        override def next = { val n = it.next; (n._1, n._2) }
    }

    override def get(key: Vector[A]) = tree.get(key)
    override def hashCode = tree.hashCode
    override def isEmpty = tree.isEmpty
    override def size = tree.size
    override def stringPrefix = "SymbolMap"

    override def -=(key: Vector[A]) = { tree.remove(key); this }
    override def +=(kv: (Vector[A], Peg[A])) = { tree.put(kv._1, kv._2); this }
    override def clear = tree.clear
    //override def clone: Map[A] = new Default(tree.clone)

}
