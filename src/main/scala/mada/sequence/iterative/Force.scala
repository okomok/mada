

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Force[+A](_1: Iterative[A]) extends Iterative[A] {
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

    override def force: Iterative[A] = this // force-force fusion
}
