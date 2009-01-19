

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B]): Vector[A] = {
        if (v.size != w.size) {
            throw new java.lang.IllegalArgumentException("size is different: " + v.size + " and " + w.size)
        }
        val (first, last) = v.pair
        val (result, _) = w.pair
        stl.Copy(v, first, last, w, result)
        v
    }
}
