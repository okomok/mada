

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Touch {
    def apply[A](v: Vector[A]): Vector[A] = {
        for (e <- v) {
            touchElem(e)
        }
        v
    }

    private def touchElem[V](e: V): V = e
}
