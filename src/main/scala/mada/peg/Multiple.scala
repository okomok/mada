

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import scala.collection.Set


private[mada] object Multiple {
    def apply[A](es: Set[A]): Peg[A] = new MultiplePeg(es)
}

private[mada] class MultiplePeg[A](es: Set[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end || !es.contains(v(start))) {
            Peg.FAILURE
        } else {
            start + 1
        }
    }
    override def width = 1
}
