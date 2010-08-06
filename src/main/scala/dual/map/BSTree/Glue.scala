

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


private[dual]
final class Glue {
     def apply[l <: BSTree, r <: BSTree](l: l, r: r): apply[l, r] =
        `if`(l.isEmpty, const0(r), `if`(r.isEmpty, const0(l), Else(l, r))).apply.asInstanceOfMapBSTree.asInstanceOf[apply[l, r]]
    type apply[l <: BSTree, r <: BSTree] =
        `if`[l#isEmpty, const0[r], `if`[r#isEmpty, const0[l], Else[l, r]]]#apply#asInstanceOfMapBSTree

    case class Else[l <: BSTree, r <: BSTree](l: l, r: r) extends Function0 {
        type self = Else[l, r]
        override  def apply: apply = `if`(l.size  > r.size,  ElseThen(l, r), ElseElse(l, r)).apply.asInstanceOf[apply]
        override type apply =        `if`[l#size# >[r#size], ElseThen[l, r], ElseElse[l, r]]#apply
    }

    case class ElseThen[l <: BSTree, r <: BSTree](l: l, r: r) extends Function0 {
        type self = ElseThen[l, r]

        private lazy val d: d = new RemoveMax().apply(l)
        private type d = RemoveMax#apply[l]

        override  def apply: apply = new Balance().apply(d._1.asInstanceOfProduct2._1, d._1.asInstanceOfProduct2._2, d._2.asInstanceOfMapBSTree, r)
        override type apply        =     Balance#  apply[d#_1#asInstanceOfProduct2#_1, d#_1#asInstanceOfProduct2#_2, d#_2#asInstanceOfMapBSTree, r]
    }

    case class ElseElse[l <: BSTree, r <: BSTree](l: l, r: r) extends Function0 {
        type self = ElseElse[l, r]

        private lazy val d: d = new RemoveMin().apply(r)
        private type d = RemoveMin#apply[r]

        override  def apply: apply = new Balance().apply(d._1.asInstanceOfProduct2._1, d._1.asInstanceOfProduct2._2, l, d._2.asInstanceOfMapBSTree)
        override type apply        =     Balance#  apply[d#_1#asInstanceOfProduct2#_1, d#_1#asInstanceOfProduct2#_2, l, d#_2#asInstanceOfMapBSTree]
    }
}


private[dual]
final class RemoveMax { // => Tuple2(Tuple2(maxKey, value), map)
     def apply[m <: BSTree](m: m): apply[m] = `if`(m.right.isEmpty, Then(m), Else(m)).apply.asInstanceOfProduct2
    type apply[m <: BSTree]                 = `if`[m#right#isEmpty, Then[m], Else[m]]#apply#asInstanceOfProduct2

    case class Then[m <: BSTree](m: m) extends Function0 {
        type self = Then[m]
        override  def apply: apply = Tuple2(Tuple2(m.key, m.value), m.left)
        override type apply        = Tuple2[Tuple2[m#key, m#value], m#left]
    }

    case class Else[m <: BSTree](m: m) extends Function0 {
        type self = Else[m]

        private lazy val d: d = new RemoveMax().apply(m.right)
        private type d = RemoveMax#apply[m#right]

        override  def apply: apply = Tuple2(d._1, new Balance().apply(m.key, m.value, m.left, d._2.asInstanceOfMapBSTree)).asInstanceOf[apply]
        override type apply        = Tuple2[d#_1,     Balance#  apply[m#key, m#value, m#left, d#_2#asInstanceOfMapBSTree]]
    }
}

private[dual]
final class RemoveMin { // => Tuple2(Tuple2(minKey, value), map)
     def apply[m <: BSTree](m: m): apply[m] = `if`(m.left.isEmpty, Then(m), Else(m)).apply.asInstanceOfProduct2
    type apply[m <: BSTree]                 = `if`[m#left#isEmpty, Then[m], Else[m]]#apply#asInstanceOfProduct2

    case class Then[m <: BSTree](m: m) extends Function0 {
        type self = Then[m]
        override  def apply: apply = Tuple2(Tuple2(m.key, m.value), m.right)
        override type apply        = Tuple2[Tuple2[m#key, m#value], m#right]
    }

    case class Else[m <: BSTree](m: m) extends Function0 {
        type self = Else[m]

        private lazy val d: d = new RemoveMin().apply(m.left)
        private type d = RemoveMin#apply[m#left]

        override  def apply: apply = Tuple2(d._1, new Balance().apply(m.key, m.value, d._2.asInstanceOfMapBSTree, m.right)).asInstanceOf[apply]
        override type apply        = Tuple2[d#_1,     Balance#  apply[m#key, m#value, d#_2#asInstanceOfMapBSTree, m#right]]
    }
}
