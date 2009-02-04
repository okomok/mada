

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Opt {
    def apply[A](p: Peg[A]): Peg[A] = p or Peg.eps[A]
}

private[mada] object OptBefore {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = q.lookAhead or (p seqAnd q.lookAhead)
}

private[mada] object OptUntil {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = q or (p seqAnd q)
}
