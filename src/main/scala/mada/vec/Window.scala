

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Window {
    def apply[A](v: Vector[A], n: Int, m: Int): Vector[A] = new WindowVector(v, n, m)
}

private[mada] class WindowVector[A](override val * : Vector[A], n: Int, m: Int) extends VectorAdapter[A, A] {
    if (n > m) {
        throw new java.lang.IllegalArgumentException("negative size: Vector.window(" + n + ", " + m + ")")
    }

    override def size = m - n
    override def mapIndex(i: Int) = n + i

    override def triple = (*, n, m) // triple-window fusion
    override def window(_n: Int, _m: Int) = *.window(n + _n, n + _m) // window-window fusion
/*
    override def sortWith(lt: (A, A) => Boolean) = * match {
        import java.util.Arrays
        case av: ArrayVector[_] => { Arrays.sort(av.array, n, m, jcl.ToComparator(lt)); this }
        case _ => super.sortWith(lt)
    }
*/
}
