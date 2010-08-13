

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

        override  def parse[xs <: List](xs: xs): parse[xs] = _aux(p.parse(xs), xs)
        override type parse[xs <: List]                    = _aux[p#parse[xs], xs]

        private lazy val pw: pw = p.width
        private type pw         = p#width

        override  def width: width =
            `if`(pw.equal(q.width), const0(pw), throw0(new UnsupportedOperationException("dual.peg.or.width"))).apply.asInstanceOfNat.asInstanceOf[width]
        override type width =
            `if`[pw#equal[q#width], const0[pw], throw0[_]]#apply#asInstanceOfNat

        private  def _aux[r <: Result, xs <: List](r: r, xs: xs): _aux[r, xs] =
            `if`(r.successful, Then(r), Else(q, xs)).apply.asInstanceOfPegResult
        private type _aux[r <: Result, xs <: List] =
            `if`[r#successful, Then[r], Else[q, xs]]#apply#asInstanceOfPegResult
    }

    final case class Then[r <: Result](r: r) extends Function0 {
        type self = Then[r]
        override  def apply: apply = Success(Left(r.get), r.next)
        override type apply        = Success[Left[r#get], r#next]
    }

    final case class Else[q <: Peg, xs <: List](q: q, xs: xs) extends Function0 {
        type self = Else[q, xs]
        override  def apply: apply = q.parse(xs).map(MakeRight).asInstanceOf[apply]
        override type apply        = q#parse[xs]#map[MakeRight]
    }

    val MakeRight = new MakeRight
    final class MakeRight extends Function1 {
        type self = MakeRight
        override  def apply[b <: Any](b: b): apply[b] = Right(b)
        override type apply[b <: Any]                 = Right[b]
    }
}
