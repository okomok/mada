

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


import java.util.ArrayList


case class Unsplit[A](_1: Iterable[Vector[A]], _2: Vector[A]) extends Forwarder[A] {
    override protected val delegate = {
        val ar = new ArrayList[A]

        val it = _1.iterator
        if (it.hasNext) {
            addVector(it.next, ar)
        }
        while (it.hasNext) {
            addVector(_2, ar)
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
