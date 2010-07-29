

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


private[mada] final class PutSeq {
     def apply[m <: Map, xs <: Seq](m: m, xs: xs): apply[m, xs] = xs.foldLeft(m, Step()).asInstanceOfMap
    type apply[m <: Map, xs <: Seq] = xs#foldLeft[m, Step]#asInstanceOfMap

    case class Step() extends Function2 {
        type self = Step
        override  def apply[b <: Any, a <: Any](b: b, a: a): apply[b, a] =
            b.asInstanceOfMap.put(a.asInstanceOfProduct2._1, a.asInstanceOfProduct2._2)
        override type apply[b <: Any, a <: Any] =
            b#asInstanceOfMap#put[a#asInstanceOfProduct2#_1, a#asInstanceOfProduct2#_2]
    }
}
