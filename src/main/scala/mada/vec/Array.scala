

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


class ArrayVector[A](val array: Array[A]) extends Vector[A] {
    override def size = array.length
    override def apply(i: Long) = array(i.toInt)
    override def update(i: Long, e: A) = array(i.toInt) = e

    override def toArray = array
}


object ToArray {
    def apply[A](v: Vector[A]): Array[A] = {
        val a = new Array[A](v.size.toInt)
        var i = 0
        v.foreach({ (e: A) => a(i) = e; i += 1 })
        a
    }
}
