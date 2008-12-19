

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2.jcl


import java.util.{ ArrayList => JavaArrayList }


class ArrayListVector[A](val arrayList: JavaArrayList[A]) extends Vector[A] {
    override def size = arrayList.size
    override def apply(i: Long) = arrayList.get(i.toInt)
    override def update(i: Long, e: A) = arrayList.set(i.toInt, e)
}


object ToArrayList {
    def apply[A](v: Vector[A]): JavaArrayList[A] = {
        var a = new JavaArrayList[A](v.size.toInt)
        v.foreach(a.add(_: A))
        a
    }
}


object ArrayList {
    def apply[A](es: A*): JavaArrayList[A] = {
        val a = new JavaArrayList[A](es.length)
        for (e <- es.elements) {
            a.add(e)
        }
        a
    }
}
