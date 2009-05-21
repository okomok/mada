

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Force[+A](_1: Sequence[A]) extends Sequence[A] {
    override def begin = k.begin
    private lazy val k = {
        val r = new java.util.ArrayList[A]
        val it = _1.begin
        while (it) {
            r.add(~it)
            it.++
        }
        fromJIterable(r)
    }

    override def force = _1.force // force-force fusion
}
