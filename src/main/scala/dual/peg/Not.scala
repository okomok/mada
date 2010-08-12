

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object Not {
     def apply[p <: Peg](p: p): apply[p] = Impl(p)
    type apply[p <: Peg]                 = Impl[p]

    final case class Impl[p <: Peg](p: p) extends AbstractPeg with ZeroWidth {
        override  def parse[xs <: List](xs: xs): parse[xs] = _aux(p.parse(xs), xs)
        override type parse[xs <: List]                    = _aux[p#parse[xs], xs]

        private  def _aux[r <: Result, xs <: List](r: r, xs: xs): _aux[r, xs] =
            `if`(r.successful, const0(Failure(xs)), const0(Success(Unit, xs))).apply.asInstanceOfPegResult.asInstanceOf[_aux[r, xs]]
        private type _aux[r <: Result, xs <: List] =
            `if`[r#successful, const0[Failure[xs]], const0[Success[Unit, xs]]]#apply#asInstanceOfPegResult
    }
}
