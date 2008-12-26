

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// Note: range(0, 100).window(20, 80).window(-20, 100) is ok.


import java.util.Arrays


object Window {
    def apply[A](v: Vector[A], n: Long, m: Long): Vector[A] = new WindowVector(v, n, m)
}

class WindowVector[A](override val * : Vector[A], n: Long, m: Long) extends VectorAdapter[A, A] {
    Assert(n <= m)

    override def size = m - n
    override def mapIndex(i: Long) = n + i

    override def window(_n: Long, _m: Long) = *.window(n + _n, n + _m) // window-window fusion

/*
    override def sort(lt: (A, A) => Boolean) = * match {
        case av: ArrayVector[_] => { Arrays.sort(av.array, n.toInt, m.toInt, jcl.ToComparator(lt)); this }
        case _ => super.sort(lt)
    }
*/
}
