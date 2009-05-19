

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Flatten {
    def apply[A](vs: Iterable[Vector[A]]): Vector[A] = {
        val ar = new java.util.ArrayList[A]
        for (v <- vs.view) {
            for (e <- v) {
                ar.add(e)
            }
        }
        vector.fromJclList(ar)
    }
}
