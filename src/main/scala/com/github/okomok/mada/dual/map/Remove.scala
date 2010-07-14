

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


private[mada] final class NodeRemove {
     def apply[m <: Map, k <: Any](m: m, k: k): apply[m, k] =
        m.ord.`match`(k, m.key, CaseLT(m, k), CaseGT(m, k), CaseEQ(m, k)).asInstanceOfMap.asInstanceOf[apply[m, k]]
    type apply[m <: Map, k <: Any] =
        m#ord#`match`[k, m#key, CaseLT[m, k], CaseGT[m, k], CaseEQ[m, k]]#asInstanceOfMap

    case class CaseLT[m <: Map, k <: Any](m: m, k: k) extends Function0 {
        override  val self = this
        override type self = CaseLT[m, k]
        override  def apply: apply = new Balance().apply(m.key, m.value, m.left.remove(k), m.right, m.ord).asInstanceOf[apply]
        override type apply        =     Balance#  apply[m#key, m#value, m#left#remove[k], m#right, m#ord]
    }

    case class CaseGT[m <: Map, k <: Any](m: m, k: k) extends Function0 {
        override  val self = this
        override type self = CaseGT[m, k]
        override  def apply: apply = new Balance().apply(m.key, m.value, m.left, m.right.remove(k), m.ord).asInstanceOf[apply]
        override type apply        =     Balance#  apply[m#key, m#value, m#left, m#right#remove[k], m#ord]
    }

    case class CaseEQ[m <: Map, k <: Any](m: m, k: k) extends Function0 {
        override  val self = this
        override type self = CaseEQ[m, k]
        override  def apply: apply = new Glue().apply(m.left, m.right, m.ord)
        override type apply        =     Glue#  apply[m#left, m#right, m#ord]
    }
}
