

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object FromList {
     def apply[ys <: List](ys: ys): apply[ys] = Impl(ys)
    type apply[ys <: List]                    = Impl[ys]

    final case class Impl[ys <: List](ys: ys) extends AbstractPeg {
        type self = Impl[ys]

        override  def parse[xs <: List](xs: xs): parse[xs] = _aux(StartsWith.apply(xs, ys), xs)
        override type parse[xs <: List]                    = _aux[StartsWith.apply[xs, ys], xs]

        private  def _aux[r <: Option, xs <: List](r: r, xs: xs): _aux[r, xs] =
            `if`(r.isEmpty, const0(Failure(xs)), Else(r)).apply.asInstanceOfPegResult.asInstanceOf[_aux[r, xs]]
        private type _aux[r <: Option, xs <: List] =
            `if`[r#isEmpty, const0[Failure[xs]], Else[r]]#apply#asInstanceOfPegResult

        override  def width: width = ys.length
        override type width        = ys#length
    }

    final case class Else[r <: Option](r: r) extends Function0 {
        type self = Else[r]
        private lazy val p: p = r.get.asInstanceOfProduct2
        private type p        = r#get#asInstanceOfProduct2
        override  def apply: apply = Success(p._1, p._2.asInstanceOfList)
        override type apply        = Success[p#_1, p#_2#asInstanceOfList]
    }
}
