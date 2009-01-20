

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B]): Vector[A] = {
        ThrowIf.differentSize(v, w)
        val (first, last) = v.pair
        val (result, _) = w.pair
        stl.Copy(v, first, last, w, result)
        v
    }
}
