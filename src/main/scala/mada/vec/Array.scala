

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


import java.util.Arrays


object ArrayVector {
    def apply[A](u: Array[A]): Vector[A] = new ArrayVector(u)
}

class ArrayVector[A](val array: Array[A]) extends Vector[A] {
    override def size = array.length
    override def apply(i: Int) = array(i)
    override def update(i: Int, e: A) = array(i) = e

//    This requires IntArrayVector for correct overload resolution.
//    override def sort(lt: (A, A) => Boolean) = { Arrays.sort(array, jcl.ToComparator(lt)); this }
}


object ToArray {
    def apply[A](v: Vector[A]): Array[A] = {
        val a = new Array[A](v.size)
        v.copyTo(Vector.arrayVector(a))
        a
    }
}
