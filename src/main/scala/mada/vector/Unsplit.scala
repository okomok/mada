

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Unsplit {
    import java.util.ArrayList

    def apply[A](vs: Iterable[Vector[A]], sep: Vector[A]): Vector[A] = {
        val ar = new ArrayList[A]

        val it = vs.elements
        if (it.hasNext) {
            addVector(it.next, ar)
        }
        while (it.hasNext) {
            addVector(sep, ar)
            addVector(it.next, ar)
        }

        vector.fromJclList(ar)
    }

    private def addVector[A](v: Vector[A], ar: ArrayList[A]): Unit = {
        for (e <- v) {
            ar.add(e)
        }
    }
}
