

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


private[dual]
object EquivTo {
     def apply[m <: Map, w <: Map, ve <: Equiv](m: m, w: w, ve: ve): apply[m, w, ve] =
        `if`(m.size.nequal(w.size), const0(`false`), new Else(m, w, ve)).apply.asInstanceOfBoolean.asInstanceOf[apply[m, w, ve]]
    type apply[m <: Map, w <: Map, ve <: Equiv] =
        `if`[m#size#nequal[w#size], const0[`false`],     Else[m, w, ve]]#apply#asInstanceOfBoolean

    class Else[m <: Map, w <: Map, ve <: Equiv](m: m, w: w, ve: ve) extends Function0 {
        type self = Else[m, w, ve]
        override  def apply: apply = m.toList.forall(new Pred(w, ve))
        override type apply        = m#toList#forall[    Pred[w, ve]]
    }

    class Pred[w <: Map, ve <: Equiv](w: w, ve: ve) extends Function1 {
        type self = Pred[w, ve]
        override  def apply[kv <: Any](kv: kv): apply[kv] =
            new PredApply(w.get(kv.asInstanceOfProduct2._1), kv.asInstanceOfProduct2._2, ve).apply.asInstanceOf[apply[kv]]
        override type apply[kv <: Any] =
                PredApply[w#get[kv#asInstanceOfProduct2#_1], kv#asInstanceOfProduct2#_2, ve]#apply
    }

    class PredApply[ov <: Option, v <: Any, ve <: Equiv](ov: ov, v: v, ve: ve) extends Function0 {
        type self = PredApply[ov, v, ve]
        override  def apply: apply = `if`(ov.isEmpty, const0(`false`), new PredApplyElse(ov, v, ve)).apply
        override type apply        = `if`[ov#isEmpty, const0[`false`],     PredApplyElse[ov, v, ve]]#apply
    }

    class PredApplyElse[ov <: Option, v <: Any, ve <: Equiv](ov: ov, v: v, ve: ve) extends Function0 {
        type self = PredApplyElse[ov, v, ve]
        override  def apply: apply = ve.equiv(ov.get, v)
        override type apply        = ve#equiv[ov#get, v]
    }
}
