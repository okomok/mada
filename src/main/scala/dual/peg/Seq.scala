

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object Seq {
     def apply[p <: Peg, q <: Peg](p: p, q: q): apply[p, q] = Impl(p, q)
    type apply[p <: Peg, q <: Peg]                          = Impl[p, q]

    final case class Impl[p <: Peg, q <: Peg](p: p, q: q) extends AbstractPeg {
        override  def parse[xs <: List](xs: xs): parse[xs] = _aux(p.parse(xs))
        override type parse[xs <: List]                    = _aux[p#parse[xs]]

        override  def width: width = p.width.plus(q.width)
        override type width        = p#width#plus[q#width]

        private  def _aux[r <: Result](r: r): _aux[r] =
            `if`(r.successful, Then(q, r), const0(r)).apply.asInstanceOfPegResult
        private type _aux[r <: Result] =
            `if`[r#successful, Then[q, r], const0[r]]#apply#asInstanceOfPegResult
    }

    final case class Then[q <: Peg, r <: Result](q: q, r: r) extends Function0 {
        type self = Then[q, r]
        override  def apply: apply = q.parse(r.next).map(MakePair(r.get)).asInstanceOf[apply]
        override type apply        = q#parse[r#next]#map[MakePair[r#get]]
    }

    final case class MakePair[a <: Any](a: a) extends Function1 {
        type self = MakePair[a]
        override  def apply[b <: Any](b: b): apply[b] = Pair(a, b)
        override type apply[b <: Any]                 = Pair[a, b]
    }
}
