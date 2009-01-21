

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Error {
    def apply[A]: Peg[A] = new ErrorPeg[A]
}

class ErrorPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        throw new Error("ErrorPeg.parse")
    }
}
