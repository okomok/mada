

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SymbolSet {
    def apply[A](vs: Vector[A]*)(implicit c: A => Ordered[A]): SymbolSet[A] = apply(vs.elements, vec.stl.Less(c))

    def apply[A](vs: Iterator[Vector[A]], lt: (A, A) => Boolean): SymbolSet[A] = {
        val set = new SymbolSet(lt)
        for (v <- vs) {
            set.+=(v)
        }
        set
    }
}

class SymbolSet[A] private (private val tree: TSTree[A, Unit]) extends Peg[A] with scala.collection.mutable.Set[Vector[A]] {
    def this(lt: (A, A) => Boolean) = this(new TSTree[A, Unit](lt))

    override def parse(v: Vector[A], first: Int, last: Int) = {
        tree.parse(v, first, last) match {
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
    protected override def stringPrefix = "SymbolSet"

// mutable.Set
    override def +=(v: Vector[A]): Unit = tree.put(v, ())
    override def -=(v: Vector[A]): Unit = tree.remove(v)
    override def clear = tree.clear
    override def clone: SymbolSet[A] = new SymbolSet(tree.clone)
}
