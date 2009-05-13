

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains utility methods operating on type <code>SymbolMap</code>.
 */
object SymbolMap {
    /**
     * @return  <code>apply(es)(c)</code>.
     */
    def apply[A](es: (Vector[A], Peg[A])*)(implicit c: Compare[A]): SymbolMap[A] = apply(es)(c)

    /**
     * Constructs <code>SymbolMet</code> containing <code>es</code> as key-and-value entries.
     */
    def apply[A](es: Iterable[(Vector[A], Peg[A])])(lt: compare.Func[A]): SymbolMap[A] = {
        val m = new SymbolMap(lt)
        for (e <- es.projection) {
            m.put(e._1, e._2)
        }
        m
    }
}


/**
 * A <code>Peg</code> to optimize the form <code>(k1 >> p1)|(k2 >> p2)|(k3 >> p3)|...</code> using ternary search tree.
 */
class SymbolMap[A] private (private val tree: TSTree[A, Peg[A]]) extends Peg[A] with scala.collection.mutable.Map[Vector[A], Peg[A]] {
    /**
     * Constructs <code>SymbolMap</code>.
     *
     * @param   lt  strict weak ordering
     */
    def this(lt: compare.Func[A]) = this(new TSTree[A, Peg[A]](lt))

    /**
     * Succeeds if any element of this set matches then its corresponding <code>Peg</code> matches.
     */
    override def parse(v: Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((p, cur)) => p.parse(v, cur, end)
            case _ => FAILURE
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
    override def stringPrefix = "SymbolMap"

// mutable.Map
    override def -=(key: Vector[A]) = { tree.remove(key); this }
    override def +=(kv: (Vector[A], Peg[A])) = { tree.put(kv._1, kv._2); this }
    override def clear = tree.clear
    override def clone: SymbolMap[A] = new SymbolMap(tree.clone)
}
