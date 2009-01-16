

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Call {
    def apply[A](f: Unit => Any): Peg[A] = Peg.eps[A].act({ (_, _, _) => f() })
}
