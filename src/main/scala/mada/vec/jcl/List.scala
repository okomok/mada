

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.{ List, Collections }


object ListVector {
    def apply[A](from: List[A]): Vector[A] = new ListVector(from)
}

class ListVector[A](val from: List[A]) extends Vector[A] {
    override def size = from.size
    override def apply(i: Int) = from.get(i)
    override def update(i: Int, e: A) = from.set(i, e)

    override def sort(lt: (A, A) => Boolean) = { Collections.sort(from, ToComparator(lt)); this }
}
