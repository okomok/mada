

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Fail {
    def apply[A]: Peg[A] = new FailPeg[A]
}

private[mada] class FailPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = Peg.FAILURE
}
