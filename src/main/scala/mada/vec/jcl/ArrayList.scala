

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.{ ArrayList, Collections }


object ArrayListVector {
    def apply[A](u: ArrayList[A]): Vector[A] = new ArrayListVector(u)
}

class ArrayListVector[A](val arrayList: ArrayList[A]) extends Vector[A] {
    override def size = arrayList.size
    override def apply(i: Long) = arrayList.get(i.toInt)
    override def update(i: Long, e: A) = arrayList.set(i.toInt, e)

    override def sort(lt: (A, A) => Boolean) = { Collections.sort(arrayList, ToComparator(lt)); this }
}


object ToArrayList {
    def apply[A](v: Vector[A]): ArrayList[A] = {
        val a = new ArrayList[A](v.size.toInt)
        v.foreach(a.add(_: A))
        a
    }
}


object NewArrayList {
    def apply[A](es: A*): ArrayList[A] = {
        val a = new ArrayList[A](es.length)
        es.foreach(a.add(_: A))
        a
    }
}
