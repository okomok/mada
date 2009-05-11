

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains utility methods operating on type <code>SymbolSet</code>.
 */
object SymbolSet {
    /**
     * @return  <code>apply(vs)(c)</code>.
     */
    def apply[A](vs: Vector[A]*)(implicit c: Compare[A]): SymbolSet[A] = apply(vs)(c)

    /**
     * Constructs <code>SymbolSet</code> containing <code>vs</code> as elements.
     */
    def apply[A](vs: Iterable[Vector[A]])(lt: compare.Func[A]): SymbolSet[A] = {
        val set = new SymbolSet(lt)
        for (v <- vs.projection) {
            set.+=(v)
        }
        set
    }
}


/**
 * A <code>Peg</code> to optimize the form <code>k1|k2|k3|...</code> using ternary search tree.
 */
class SymbolSet[A] private (private val tree: TSTree[A, Unit]) extends Peg[A] with scala.collection.mutable.Set[Vector[A]] {
    /**
     * Constructs <code>SymbolSet</code>.
     *
     * @param   lt  strict weak ordering
     */
    def this(lt: compare.Func[A]) = this(new TSTree[A, Unit](lt))

    /**
     * Succeeds if any element of this set matches.
     */
    override def parse(v: Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((_, cur)) => cur
            case _ => Peg.FAILURE
        }
    }

// Collection
    override def elements = new Iterator[Vector[A]] {
        private val it = tree.elements
        override def hasNext = it.hasNext
        override def next = it.next._1
    }

// Set
    override def contains(v: Vector[A]) = tree.containsKey(v)
    override def hashCode = tree.hashCode
    override def isEmpty = tree.isEmpty
    override def size = tree.size
    override def stringPrefix = "SymbolSet"

// mutable.Set
    override def +=(v: Vector[A]): Unit = tree.put(v, ())
    override def -=(v: Vector[A]): Unit = tree.remove(v)
    override def clear = tree.clear
    override def clone: SymbolSet[A] = new SymbolSet(tree.clone)
}
