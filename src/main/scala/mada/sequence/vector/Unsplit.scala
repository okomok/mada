

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


import java.util.ArrayList


case class Unsplit[A](_1: Iterative[Vector[A]], _2: Vector[A]) extends Forwarder[A] {
    override protected val delegate = {
        val ar = new ArrayList[A]

        val it = _1.begin
        if (it) {
            addVector(~it, ar)
            it.++
        }
        while (it) {
            addVector(_2, ar)
            addVector(~it, ar)
            it.++
        }

        vector.fromJList(ar)
    }

    private def addVector[A](v: Vector[A], ar: ArrayList[A]): Unit = {
        for (e <- v) {
            ar.add(e)
        }
    }
}
