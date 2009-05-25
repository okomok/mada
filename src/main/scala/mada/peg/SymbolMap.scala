

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SymbolMap {

    /**
     * @return  <code>fromIterable(es)(c)</code>.
     */
    def apply[A](es: (Vector[A], Peg[A])*)(implicit c: Compare[A]): SymbolMap[A] = fromIterable(es)(c)

    /**
     * Constructs <code>SymbolMet</code> containing <code>es</code> as key-and-value entries.
     */
    def fromIterable[A](es: Iterable[(Vector[A], Peg[A])])(lt: compare.Func[A]): SymbolMap[A] = {
        val r = new SymbolMap(lt)
        for (e <- es.view) {
            r += Tuple2(e._1, e._2)
        }
        r
    }

}


/**
 * A <code>Peg</code> to optimize the form <code>(k1 >> p1)|(k2 >> p2)|(k3 >> p3)|...</code>.
 */
class SymbolMap[A](_1: compare.Func[A]) extends Peg[A] with scala.collection.mutable.Map[Vector[A], Peg[A]] {

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
