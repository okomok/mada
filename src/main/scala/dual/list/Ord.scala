

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


import ordering.{LT, GT, EQ}


private[dual]
final class Ord[eo <: Ordering](eo: eo) extends ordering.AbstractOrdering {
    type self = Ord[eo]

    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] =
        _compare(x.asInstanceOfList, y.asInstanceOfList)
    override type compare[x <: Any, y <: Any] =
        _compare[x#asInstanceOfList, y#asInstanceOfList]

    private  def _compare[xs <: List, ys <: List](xs: xs, ys: ys): _compare[xs, ys] =
        `if`(xs.isEmpty,
            `if`(ys.isEmpty, const0(EQ), const0(LT)),
            `if`(ys.isEmpty, const0(GT), Else(xs, ys))
        ).apply.asInstanceOfOrderingResult.asInstanceOf[_compare[xs, ys]]

    private type _compare[xs <: List, ys <: List] =
        `if`[xs#isEmpty,
            `if`[ys#isEmpty, const0[EQ], const0[LT]],
            `if`[ys#isEmpty, const0[GT], Else[xs, ys]]
        ]#apply#asInstanceOfOrderingResult

    case class Else[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = Else[xs, ys]
        override  def apply: apply =
            eo.`match`(xs.head, ys.head, const0(LT), const0(GT), CaseEQ(xs, ys)).asInstanceOfOrderingResult.asInstanceOf[apply]
        override type apply =
            eo#`match`[xs#head, ys#head, const0[LT], const0[GT], CaseEQ[xs, ys]]#asInstanceOfOrderingResult
    }

    case class CaseEQ[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = CaseEQ[xs, ys]
        override  def apply: apply = _compare(xs.tail, ys.tail)
        override type apply        = _compare[xs#tail, ys#tail]
    }
}
