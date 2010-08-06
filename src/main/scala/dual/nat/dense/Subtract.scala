

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[dual]
final class Subtract {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        new _Match().apply(xs, ys,
            const0(Nil),
            throw0(new UnsupportedOperationException("dual.nat.dense.Nil.subtract positive")),
            const0(xs),
            ConsMatch(xs, ys,
                CaseXX(xs, ys),
                CaseTF(xs, ys),
                CaseFT(xs, ys),
                CaseXX(xs, ys)
            )
        ).asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]

    type apply[xs <: Dense, ys <: Dense] =
        _Match#apply[xs, ys,
            const0[Nil],
            throw0[_],
            const0[xs],
            ConsMatch[xs, ys,
                CaseXX[xs, ys],
                CaseTF[xs, ys],
                CaseFT[xs, ys],
                CaseXX[xs, ys]
            ]
        ]#asInstanceOfNatDense

    case class CaseXX[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        type self = CaseXX[xs, ys]
        override  def apply: apply = new ConsFalse().apply(xs.tail - ys.tail).asInstanceOf[apply]
        override type apply = ConsFalse#apply[xs#tail# -[ys#tail]]
    }

    case class CaseTF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        type self = CaseTF[xs, ys]
        private   def xst_sub_yst: xst_sub_yst = (xs.tail - ys.tail).asInstanceOf[xst_sub_yst]
        private  type xst_sub_yst = xs#tail# -[ys#tail]
        override  def apply: apply = Cons(`true`, xst_sub_yst)
        override type apply = Cons[`true`, xst_sub_yst]
    }

    case class CaseFT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        type self = CaseFT[xs, ys]
        override  def apply: apply = Cons(`true`, xs.tail.decrement - ys.tail).asInstanceOf[apply]
        override type apply = Cons[`true`, xs#tail#decrement# -[ys#tail]]
    }
}
