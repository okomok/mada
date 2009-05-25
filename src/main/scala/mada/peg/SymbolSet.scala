

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SymbolSet {

    /**
     * @return  <code>fromIterable(vs)(c)</code>.
     */
    def apply[A](vs: Vector[A]*)(implicit c: Compare[A]): SymbolSet[A] = fromIterable(vs)(c)

    /**
     * Constructs <code>Set</code> containing <code>vs</code> as elements.
     */
    def fromIterable[A](vs: Iterable[Vector[A]])(lt: compare.Func[A]): SymbolSet[A] = {
        val r = new SymbolSet(lt)
        for (v <- vs.view) {
            r += v
        }
        r
    }

}


/**
 * A <code>Peg</code> to optimize the form <code>k1|k2|k3|...</code>.
 */
class SymbolSet[A](_1: compare.Func[A]) extends Peg[A] with scala.collection.mutable.Set[Vector[A]] {

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
