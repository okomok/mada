

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


private[mada] final class NodePut {
     def apply[m <: BSTree, k <: Any, v <: Any](m: m, k: k, v: v): apply[m, k, v] =
        m.ord.`match`(k, m.key, CaseLT(m, k, v), CaseGT(m, k, v), CaseEQ(m, k, v)).asInstanceOfMapBSTree.asInstanceOf[apply[m, k, v]]
    type apply[m <: BSTree, k <: Any, v <: Any] =
        m#ord#`match`[k, m#key, CaseLT[m, k, v], CaseGT[m, k, v], CaseEQ[m, k, v]]#asInstanceOfMapBSTree

    case class CaseLT[m <: BSTree, k <: Any, v <: Any](m: m, k: k, v: v) extends Function0 {
        override  val self = this
        override type self = CaseLT[m, k, v]
        override  def apply: apply = new Balance().apply(m.key, m.value, m.left.put(k, v), m.right, m.ord).asInstanceOf[apply]
        override type apply        =     Balance#  apply[m#key, m#value, m#left#put[k, v], m#right, m#ord]
    }

    case class CaseGT[m <: BSTree, k <: Any, v <: Any](m: m, k: k, v: v) extends Function0 {
        override  val self = this
        override type self = CaseGT[m, k, v]
        override  def apply: apply = new Balance().apply(m.key, m.value, m.left, m.right.put(k, v), m.ord).asInstanceOf[apply]
        override type apply        =     Balance#  apply[m#key, m#value, m#left, m#right#put[k, v], m#ord]
    }

    case class CaseEQ[m <: BSTree, k <: Any, v <: Any](m: m, k: k, v: v) extends Function0 {
        override  val self = this
        override type self = CaseEQ[m, k, v]
        override  def apply: apply = Node(m.size, k, v, m.left, m.right, m.ord)
        override type apply        = Node[m#size, k, v, m#left, m#right, m#ord]
    }
}
