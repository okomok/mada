

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


private[mada] class Glue {
     def apply[l <: BSTree, r <: BSTree, o <: Ordering](l: l, r: r, o: o): apply[l, r, o] =
        `if`(l.isEmpty, always0(r), `if`(r.isEmpty, always0(l), Else(l, r, o))).apply.asInstanceOfMapBSTree.asInstanceOf[apply[l, r, o]]
    type apply[l <: BSTree, r <: BSTree, o <: Ordering] =
        `if`[l#isEmpty, always0[r], `if`[r#isEmpty, always0[l], Else[l, r, o]]]#apply#asInstanceOfMapBSTree

    case class Else[l <: BSTree, r <: BSTree, o <: Ordering](l: l, r: r, o: o) extends Function0 {
        override  val self = this
        override type self = Else[l, r, o]
        override  def apply: apply = `if`(l.size > r.size, ElseThen(l, r, o), ElseElse(l, r, o)).apply
        override type apply = `if`[l#size# >[r#size], ElseThen[l, r, o], ElseElse[l, r, o]]#apply
    }

    case class ElseThen[l <: BSTree, r <: BSTree, o <: Ordering](l: l, r: r, o: o) extends Function0 {
        override  val self = this
        override type self = ElseThen[l, r, o]

        private lazy val d: d = new RemoveMax().apply(l)
        private type d = RemoveMax#apply[l]

        override  def apply: apply = new Balance().apply(d._1.asInstanceOfProduct2._1, d._1.asInstanceOfProduct2._2, d._2.asInstanceOfMapBSTree, r, o)
        override type apply        =     Balance#  apply[d#_1#asInstanceOfProduct2#_1, d#_1#asInstanceOfProduct2#_2, d#_2#asInstanceOfMapBSTree, r, o]
    }

    case class ElseElse[l <: BSTree, r <: BSTree, o <: Ordering](l: l, r: r, o: o) extends Function0 {
        override  val self = this
        override type self = ElseElse[l, r, o]

        private lazy val d: d = new RemoveMin().apply(r)
        private type d = RemoveMin#apply[r]

        override  def apply: apply = new Balance().apply(d._1.asInstanceOfProduct2._1, d._1.asInstanceOfProduct2._2, l, d._2.asInstanceOfMapBSTree, o)
        override type apply        =     Balance#  apply[d#_1#asInstanceOfProduct2#_1, d#_1#asInstanceOfProduct2#_2, l, d#_2#asInstanceOfMapBSTree, o]
    }
}


private[mada] class RemoveMax { // => Tuple2(Tuple2(maxKey, value), map)
     def apply[m <: BSTree](m: m): apply[m] = `if`(m.right.isEmpty, Then(m), Else(m)).apply.asInstanceOfProduct2
    type apply[m <: BSTree]                 = `if`[m#right#isEmpty, Then[m], Else[m]]#apply#asInstanceOfProduct2

    case class Then[m <: BSTree](m: m) extends Function0 {
        override  val self = this
        override type self = Then[m]
        override  def apply: apply = Tuple2(Tuple2(m.key, m.value), m.left)
        override type apply        = Tuple2[Tuple2[m#key, m#value], m#left]
    }

    case class Else[m <: BSTree](m: m) extends Function0 {
        override  val self = this
        override type self = Else[m]

        private lazy val d: d = new RemoveMax().apply(m.right)
        private type d = RemoveMax#apply[m#right]

        override  def apply: apply = Tuple2(d._1, new Balance().apply(m.key, m.value, m.left, d._2.asInstanceOfMapBSTree, m.ord)).asInstanceOf[apply]
        override type apply        = Tuple2[d#_1,     Balance#  apply[m#key, m#value, m#left, d#_2#asInstanceOfMapBSTree, m#ord]]
    }
}

private[mada] class RemoveMin { // => Tuple2(Tuple2(minKey, value), map)
     def apply[m <: BSTree](m: m): apply[m] = `if`(m.left.isEmpty, Then(m), Else(m)).apply.asInstanceOfProduct2
    type apply[m <: BSTree]                 = `if`[m#left#isEmpty, Then[m], Else[m]]#apply#asInstanceOfProduct2

    case class Then[m <: BSTree](m: m) extends Function0 {
        override  val self = this
        override type self = Then[m]
        override  def apply: apply = Tuple2(Tuple2(m.key, m.value), m.right)
        override type apply        = Tuple2[Tuple2[m#key, m#value], m#right]
    }

    case class Else[m <: BSTree](m: m) extends Function0 {
        override  val self = this
        override type self = Else[m]

        private lazy val d: d = new RemoveMin().apply(m.left)
        private type d = RemoveMin#apply[m#left]

        override  def apply: apply = Tuple2(d._1, new Balance().apply(m.key, m.value, d._2.asInstanceOfMapBSTree, m.right, m.ord)).asInstanceOf[apply]
        override type apply        = Tuple2[d#_1,     Balance#  apply[m#key, m#value, d#_2#asInstanceOfMapBSTree, m#right, m#ord]]
    }
}
