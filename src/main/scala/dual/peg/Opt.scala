

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object Opt {
     def apply[p <: Peg](p: p): apply[p] = Impl(p)
    type apply[p <: Peg]                 = Impl[p]

    final case class Impl[p <: Peg](p: p) extends AbstractPeg {
        override  def parse[xs <: List](xs: xs): parse[xs] = _aux(p.parse(xs))
        override type parse[xs <: List]                    = _aux[p#parse[xs]]

        private  def _aux[r <: Result](r: r): _aux[r] =
            `if`(r.successful, Then(r), const0(Success(None, r.next))).apply.asInstanceOfPegResult.asInstanceOf[_aux[r]]
        private type _aux[r <: Result] =
            `if`[r#successful, Then[r], const0[Success[None, r#next]]]#apply#asInstanceOfPegResult
    }

    final case class Then[r <: Result](r: r) extends Function0 {
        type self = Then[r]
        override  def apply: apply = Success(Some(r.get), r.next)
        override type apply        = Success[Some[r#get], r#next]
    }
}