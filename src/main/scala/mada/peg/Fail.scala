

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Fail {
    def apply[A]: Peg[A] = new FailPeg[A]
}

class FailPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = Peg.FAILURE
}
