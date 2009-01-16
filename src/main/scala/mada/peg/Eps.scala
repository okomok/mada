

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Eps {
    def apply[A]: Peg[A] = new EpsPeg[A]
}

class EpsPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = first
    override def length = 0
}
