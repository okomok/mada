

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Try {
    def apply[A](p: Peg[A]): Catch[A] = new Catch(p)
}

/**
 * Intermediate class for pseudo try-catch expression.
 */
sealed class Catch[A](p: Peg[A]) {
    def `catch`(f: Throwable => Peg[A]): Peg[A] = new TryCatchPeg(p, f)
}

private[mada] class TryCatchPeg[A](override val self: Peg[A], f: Throwable => Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        try {
            self.parse(v, start, end)
        } catch {
            case x: Throwable => f(x).parse(v, start, end)
        }
    }
}
