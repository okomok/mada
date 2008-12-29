

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.{ List, Collections }


object ListVector {
    def apply[A](u: List[A]): Vector[A] = new ListVector(u)
}

class ListVector[A](list: List[A]) extends Vector[A] {
    override def size = list.size
    override def apply(i: Long) = list.get(i.toInt)
    override def update(i: Long, e: A) = list.set(i.toInt, e)

    override def sort(lt: (A, A) => Boolean) = { Collections.sort(list, ToComparator(lt)); this }
}
