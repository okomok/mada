

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.{ List, Collections }


private[mada] object ListToVector {
    def apply[A](from: List[A]): Vector[A] = new ListVector(from)
}

private[mada] class ListVector[A](val from: List[A]) extends Vector[A] {
    override def start = 0
    override def end = from.size
    override def apply(i: Int) = from.get(i)
    override def update(i: Int, e: A) = from.set(i, e)

    override def sortBy(lt: Compare.Predicate[A]) = {
        Collections.sort(from, Compare.toComparator(lt))
        this
    }

    override def stableSortBy(lt: Compare.Predicate[A]) = {
        Collections.sort(from, Compare.toComparator(lt))
        this
    }
}
