

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object Matches {
     def apply[p <: Peg, xs <: List](p: p, xs: xs): apply[p, xs] = _aux(p.parse(xs))
    type apply[p <: Peg, xs <: List]                             = _aux[p#parse[xs]]

    private  def _aux[r <: Result](r: r): _aux[r] =
        `if`(r.successful, Then(r), const0(`false`)).apply.asInstanceOfBoolean
    private type _aux[r <: Result] =
        `if`[r#successful, Then[r], const0[`false`]]#apply#asInstanceOfBoolean

    final case class Then[r <: Result](r: r) extends Function0 {
        type self = Then[r]
        override  def apply: apply = r.next.isEmpty
        override type apply        = r#next#isEmpty
    }
}
