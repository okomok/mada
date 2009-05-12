

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import scala.collection.Map


private[mada] object Switch {
    def apply[A](es: Map[A, Peg[A]]): Peg[A] = new SwitchPeg(es)
}

private[mada] class SwitchPeg[A](es: Map[A, Peg[A]]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end) {
            FAILURE
        } else {
            val p = es.get(v(start))
            if (p.isEmpty) {
                FAILURE
            } else {
                p.get.parse(v, start + 1, end)
            }
        }
    }
}
