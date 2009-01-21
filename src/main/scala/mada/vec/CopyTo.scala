

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B]): Vector[A] = {
        ThrowIf.differentSize(v, w, "copyTo")
        val (x, first, last) = v.triple
        val (y, result, _) = w.triple
        stl.Copy(x, first, last, y, result)
        v
    }
}
