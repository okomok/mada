

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.{ List, Collections }


object ListVector {
    def apply[A](u: List[A]): Vector[A] = new ListVector(u)
}

class ListVector[A](list: List[A]) extends Vector[A] {
    override def size = list.size
    override def apply(i: Int) = list.get(i)
    override def update(i: Int, e: A) = list.set(i, e)

    override def sort(lt: (A, A) => Boolean) = { Collections.sort(list, ToComparator(lt)); this }
}
