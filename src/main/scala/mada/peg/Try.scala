

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Try[A](_1: Peg[A]) {
    def `catch`(f: Throwable => Peg[A]): TryCatch[A] = TryCatch(_1, f)
}

case class TryCatch[A](_1: Peg[A], _2: Throwable => Peg[A]) extends Forwarder[A] {
    override protected val delegate: Peg[A] = TryCatchFinally(_1, _2, function.empty1)
    def `finally`(g: Action[A]): Peg[A] = TryCatchFinally(_1, _2, g)
}

case class TryCatchFinally[A](_1: Peg[A], _2: Throwable => Peg[A], _3: Action[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        try {
            _1.parse(v, start, end)
        } catch {
            case x: Throwable => _2(x).parse(v, start, end)
        } finally {
            _3(v(start, end))
        }
    }
}
