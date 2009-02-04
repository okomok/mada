

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Call {
    def apply[A](f: Unit => Any): Peg[A] = Pegs.eps[A].act({ _ => f() })
}
