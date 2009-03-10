

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Flatten {
    def apply[A](vs: Iterable[Vector[A]]): Vector[A] = {
        val ar = new java.util.ArrayList[A]
        for (v <- vs.projection) {
            for (e <- v) {
                ar.add(e)
            }
        }
        Vector.fromJclList(ar)
    }
}
