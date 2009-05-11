

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Try {
    def apply[A](p: Peg[A]): Try[A] = new Try(p)
}

/**
 * Intermediate class for pseudo try-catch expression.
 */
sealed class Try[A](p: Peg[A]) {
    /**
     * @return  <code>new TryCatch(p, f)</code>.
     */
    def `catch`(f: Throwable => Peg[A]): TryCatch[A] = new TryCatch(p, f)

    /**
     * Intermediate peg for pseudo try-catch-finally expression.
     */
    sealed class TryCatch[A](p: Peg[A], f: Throwable => Peg[A]) extends PegProxy[A] {
        override val self = new TryCatchFinallyPeg(p, f, function.empty1)
        def `finally`(g: Peg.Action[A]): Peg[A] = new TryCatchFinallyPeg(p, f, g)
    }
}

private[mada] class TryCatchFinallyPeg[A](p: Peg[A], f: Throwable => Peg[A], g: Peg.Action[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        try {
            p.parse(v, start, end)
        } catch {
            case x: Throwable => f(x).parse(v, start, end)
        } finally {
            g(v(start, end))
        }
    }
}
