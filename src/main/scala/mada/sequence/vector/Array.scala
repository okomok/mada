

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


import java.util.Arrays


private[mada] object FromArray {
    def apply[A](from: Array[A]): Vector[A] = new ArrayVector(from)
}

private[mada] class ArrayVector[A](from: Array[A]) extends Vector[A] {
    override def start = 0
    override def end = from.length
    override def apply(i: Int) = from(i)
    override def update(i: Int, e: A) = from(i) = e

//    This requires IntFromArray for correct overload resolution.
//    override def sortBy(lt: compare.Func[A]) = { Arrays.sort(from, LessComparator(lt)); this }
}


private[mada] object ToArray {
    def apply[A](from: Vector[A]): Array[A] = {
        val a = new Array[A](from.size)
        from.copyTo(vector.fromArray(a))
        a
    }
}
