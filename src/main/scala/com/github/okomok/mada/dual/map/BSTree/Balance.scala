

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


private[mada] object Balance {
     val delta: delta = nat.peano._5
    type delta = nat.peano._5
     val ratio: ratio = nat.peano._2
    type ratio = nat.peano._2
}


@visibleForTesting
final class Balance {
    import Balance._

     def apply[k <: Any, v <: Any, l <: BSTree, r <: BSTree](k: k, v: v, l: l, r: r): apply[k, v, l, r] =
        `if`(l.size  + r.size   <= nat.peano._1,
            const0(Node(k, v, l, r)),
            `if`(r.size  >= delta  ** l.size,
                RotateL(k, v, l, r),
                `if`(l.size  >= delta  ** r.size,
                    RotateR(k, v, l, r),
                    const0(Node(k, v, l, r))
                )
            )
        ).apply.asInstanceOf[apply[k, v, l, r]]

    type apply[k <: Any, v <: Any, l <: BSTree, r <: BSTree] =
        `if`[l#size# +[r#size]# <=[nat.peano._1],
            const0[Node[k, v, l, r]],
            `if`[r#size# >=[delta# **[l#size]],
                RotateL[k, v, l, r],
                `if`[l#size# >=[delta# **[r#size]],
                    RotateR[k, v, l, r],
                    const0[Node[k, v, l, r]]
                ]
            ]
        ]#apply
}


private[mada] case class RotateL[k <: Any, v <: Any, l <: BSTree, r <: BSTree](k: k, v: v, l: l, r: r) extends Function0 {
    import Balance._
    override  val self = this
    override type self = RotateL[k, v, l, r]
    override  def apply: apply = `if`(r.left.size  < ratio  ** r.right.size  , SingleL(k, v, l, r), DoubleL(k, v, l, r)).apply.asInstanceOf[apply]
    override type apply =        `if`[r#left#size# <[ratio# **[r#right#size]], SingleL[k, v, l, r], DoubleL[k, v, l, r]]#apply
}

private[mada] case class RotateR[k <: Any, v <: Any, l <: BSTree, r <: BSTree](k: k, v: v, l: l, r: r) extends Function0 {
    import Balance._
    override  val self = this
    override type self = RotateR[k, v, l, r]
    override  def apply: apply = `if`(l.right.size  < ratio  ** l.left.size  , SingleR(k, v, l, r), DoubleR(k, v, l, r)).apply.asInstanceOf[apply]
    override type apply =        `if`[l#right#size# <[ratio# **[l#left#size]], SingleR[k, v, l, r], DoubleR[k, v, l, r]]#apply
}


private[mada] case class SingleL[k <: Any, v <: Any, l <: BSTree, r <: BSTree](k: k, v: v, l: l, r: r) extends Function0 {
    override  val self = this
    override type self = SingleL[k, v, l, r]
    override  def apply: apply = Node(r.key, r.value, Node(k, v, l, r.left), r.right).asInstanceOf[apply]
    override type apply =        Node[r#key, r#value, Node[k, v, l, r#left], r#right]
}

private[mada] case class SingleR[k <: Any, v <: Any, l <: BSTree, r <: BSTree](k: k, v: v, l: l, r: r) extends Function0 {
    override  val self = this
    override type self = SingleR[k, v, l, r]
    override  def apply: apply = Node(l.key, l.value, l.left, Node(k, v, l.right, r)).asInstanceOf[apply]
    override type apply =        Node[l#key, l#value, l#left, Node[k, v, l#right, r]]
}


private[mada] case class DoubleL[k <: Any, v <: Any, l <: BSTree, r <: BSTree](k: k, v: v, l: l, r: r) extends Function0 {
    override  val self = this
    override type self = DoubleL[k, v, l, r]
    override  def apply: apply = Node(r.left.key, r.left.value, Node(k, v, l, r.left.left), Node(r.key, r.value, r.left.right, r.right)).asInstanceOf[apply]
    override type apply =        Node[r#left#key, r#left#value, Node[k, v, l, r#left#left], Node[r#key, r#value, r#left#right, r#right]]
}

private[mada] case class DoubleR[k <: Any, v <: Any, l <: BSTree, r <: BSTree](k: k, v: v, l: l, r: r) extends Function0 {
    override  val self = this
    override type self = DoubleR[k, v, l, r]
    override  def apply: apply = Node(l.right.key, l.right.value, Node(l.key, l.value, l.left, l.right.left), Node(k, v, l.right.right, r)).asInstanceOf[apply]
    override type apply =        Node[l#right#key, l#right#value, Node[l#key, l#value, l#left, l#right#left], Node[k, v, l#right#right, r]]
}

