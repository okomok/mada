

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


import java.util.Arrays


private[mada] object ArrayVector {
    def apply[A](from: Array[A]): Vector[A] = new ArrayVector(from)
}

private[mada] class ArrayVector[A](from: Array[A]) extends Vector[A] {
    override def size = from.length
    override def apply(i: Int) = from(i)
    override def update(i: Int, e: A) = from(i) = e

//    This requires IntArrayVector for correct overload resolution.
//    override def sortWith(lt: (A, A) => Boolean) = { Arrays.sort(from, jcl.ToComparator(lt)); this }
}


private[mada] object ToArray {
    def apply[A](from: Vector[A]): Array[A] = {
        val a = new Array[A](from.size)
        from.copyTo(Vector.arrayVector(a))
        a
    }
}
