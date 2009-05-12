

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Error {
    def apply[A]: Peg[A] = impl.asInstanceOf[Peg[A]]
    private val impl: Peg[Any] = new ErrorPeg[Any]
}

private[mada] class ErrorPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        throw new java.lang.AssertionError("error")
    }
}
