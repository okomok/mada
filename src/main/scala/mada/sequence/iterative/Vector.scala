

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class FromVector[A](_1: Vector[A]) extends Forwarder[A] {
    override protected lazy val delegate = _1.toIterative
}

case class ToVector[A](_1: Iterative[A]) extends vector.Forwarder[A] {
    override protected lazy val delegate = {
        val it = _1.begin
        val a = new java.util.ArrayList[A]
        while (it) {
            a.add(~it)
            it.++
        }
        vector.fromJclList(a)
    }
}
