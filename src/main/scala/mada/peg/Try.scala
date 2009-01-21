

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Try {
    def apply[A](p: Peg[A]) = new {
        def catch_(f: Throwable => Peg[A]) = new TryCatchPeg(p, f)
    }
}

class TryCatchPeg[A](override val self: Peg[A], f: Throwable => Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        try {
            self.parse(v, start, end)
        } catch {
            case x: Throwable => f(x).parse(v, start, end)
        }
    }
}
