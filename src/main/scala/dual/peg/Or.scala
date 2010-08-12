

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object Or {
     def apply[p <: Peg, q <: Peg](p: p, q: q): apply[p, q] = Impl(p, q)
    type apply[p <: Peg, q <: Peg]                          = Impl[p, q]

    final case class Impl[p <: Peg, q <: Peg](p: p, q: q) extends AbstractPeg {
        type self = Impl[p, q]

        override  def parse[xs <: List](xs: xs): parse[xs] = _aux(p.parse(xs))
        override type parse[xs <: List]                    = _aux[p#parse[xs]]

        private lazy val pw: pw = p.width
        private type pw         = p#width

        override  def width: width =
            `if`(pw.equal(q.width), const0(pw), throw0(new UnsupportedOperationException("dual.peg.or.width"))).apply.asInstanceOfNat.asInstanceOf[width]
        override type width =
            `if`[pw#equal[q#width], const0[pw], throw0[_]]#apply#asInstanceOfNat

        private  def _aux[r <: Result](r: r): _aux[r] =
            `if`(r.successful, const0(r), Else(q, r)).apply.asInstanceOfPegResult
        private type _aux[r <: Result] =
            `if`[r#successful, const0[r], Else[q, r]]#apply#asInstanceOfPegResult
    }

    final case class Else[q <: Peg, r <: Result](q: q, r: r) extends Function0 {
        type self = Else[q, r]
        override  def apply: apply = q.parse(r.next)
        override type apply        = q#parse[r#next]
    }
}
