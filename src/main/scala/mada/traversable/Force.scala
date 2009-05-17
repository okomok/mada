

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Force[+A](_1: Traversable[A]) extends Forwarder[A] {
    override protected lazy val delegate = {
        val r = new java.util.ArrayList[A]
        val t = _1.begin
        while (t) {
            r.add(~t)
            t.++
        }
        fromJIterable(r)
    }

    override def force = _1.force // force-force fusion
}
