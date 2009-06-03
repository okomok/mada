

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Flatten[A](_1: Iterative[Iterative[A]]) extends Forwarder[A] {
    override protected val delegate = {
        val ar = new java.util.ArrayList[A]
        for (v <- _1) {
            for (e <- v) {
                ar.add(e)
            }
        }
        vector.fromJclList(ar)
    }
}
