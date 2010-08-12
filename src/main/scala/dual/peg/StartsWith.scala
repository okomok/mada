

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object StartsWith {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] =
        `if`(ys.isEmpty,
            const0(Some(Pair(Nil, xs))),
            `if`(xs.isEmpty,
                const0(None),
                Else(xs, ys)
            )
        ).apply.asInstanceOfOption.asInstanceOf[apply[xs, ys]]
    type apply[xs <: List, ys <: List] =
        `if`[ys#isEmpty,
            const0[Some[Pair[Nil, xs]]],
            `if`[xs#isEmpty,
                const0[None],
                Else[xs, ys]
            ]
        ]#apply#asInstanceOfOption

    final case class Else[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = Else[xs, ys]
        override  def apply: apply =
            `if`(xs.head.naturalOrdering.equiv(xs.head, ys.head), ElseThen(xs, ys), const0(None)).apply.asInstanceOf[apply]
        override type apply =
            `if`[xs#head#naturalOrdering#equiv[xs#head, ys#head], ElseThen[xs, ys], const0[None]]#apply
    }

    final case class ElseThen[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = ElseThen[xs, ys]
        private lazy val r: r = StartsWith.apply(xs.tail, ys.tail)
        private type r        = StartsWith.apply[xs#tail, ys#tail]
        override  def apply: apply = `if`(r.isEmpty, const0(None), ElseThenElse(xs.head, r)).apply.asInstanceOf[apply]
        override type apply        = `if`[r#isEmpty, const0[None], ElseThenElse[xs#head, r]]#apply
    }

    final case class ElseThenElse[x <: Any, r <: Option](x: x, r: r) extends Function0 {
        type self = ElseThenElse[x, r]
        private lazy val p: p = r.get.asInstanceOfProduct2
        private type p        = r#get#asInstanceOfProduct2
        override  def apply: apply = Pair(x :: p._1.asInstanceOfList, p._2)
        override type apply        = Pair[x :: p#_1#asInstanceOfList, p#_2]
    }
}
