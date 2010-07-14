

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


private[mada] object Balance {
     val delta: delta = nat.peano._5
    type delta = nat.peano._5
     val ratio: ratio = nat.peano._2
    type ratio = nat.peano._2
}


@visibleForTesting
final class Balance {
    import Balance._

     def apply[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o): apply[k, v, l, r, o] =
        `if`(l.size  + r.size   <= nat.peano._1,
            NewNode(k, v, l, r, o),
            `if`(r.size  >= delta  ** l.size,
                RotateL(k, v, l, r, o),
                `if`(l.size  >= delta  ** r.size,
                    RotateR(k, v, l, r, o),
                    NewNode(k, v, l, r, o)
                )
            )
        ).apply.asInstanceOf[apply[k, v, l, r, o]]

    type apply[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering] =
        `if`[l#size# +[r#size]# <=[nat.peano._1],
            NewNode[k, v, l, r, o],
            `if`[r#size# >=[delta# **[l#size]],
                RotateL[k, v, l, r, o],
                `if`[l#size# >=[delta# **[r#size]],
                    RotateR[k, v, l, r, o],
                    NewNode[k, v, l, r, o]
                ]
            ]
        ]#apply
}


private[mada] case class NewNode[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    override  val self = this
    override type self = NewNode[k, v, l, r, o]
    private   def newsize: newsize = (l.size  + r.size).increment.asInstanceOf[newsize]
    private  type newsize          =  l#size# +[r#size]#increment
    override  def apply: apply = Node(newsize, k, v, l, r, o)
    override type apply        = Node[newsize, k, v, l, r, o]
}


private[mada] case class RotateL[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    import Balance._
    override  val self = this
    override type self = RotateL[k, v, l, r, o]
    override  def apply: apply = `if`(r.left.size  < ratio  ** r.right.size  , SingleL(k, v, l, r, o), DoubleL(k, v, l, r, o)).apply.asInstanceOf[apply]
    override type apply =        `if`[r#left#size# <[ratio# **[r#right#size]], SingleL[k, v, l, r, o], DoubleL[k, v, l, r, o]]#apply
}

private[mada] case class RotateR[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    import Balance._
    override  val self = this
    override type self = RotateR[k, v, l, r, o]
    override  def apply: apply = `if`(l.right.size  < ratio  ** l.left.size  , SingleR(k, v, l, r, o), DoubleR(k, v, l, r, o)).apply.asInstanceOf[apply]
    override type apply =        `if`[l#right#size# <[ratio# **[l#left#size]], SingleR[k, v, l, r, o], DoubleR[k, v, l, r, o]]#apply
}


private[mada] case class SingleL[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    override  val self = this
    override type self = SingleL[k, v, l, r, o]
    override  def apply: apply = NewNode(r.key, r.value, NewNode(k, v, l, r.left, o).apply, r.right, o).apply.asInstanceOf[apply]
    override type apply =        NewNode[r#key, r#value, NewNode[k, v, l, r#left, o]#apply, r#right, o]#apply
}

private[mada] case class SingleR[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    override  val self = this
    override type self = SingleR[k, v, l, r, o]
    override  def apply: apply = NewNode(l.key, l.value, l.left, NewNode(k, v, l.right, r, o).apply, o).apply.asInstanceOf[apply]
    override type apply =        NewNode[l#key, l#value, l#left, NewNode[k, v, l#right, r, o]#apply, o]#apply
}


private[mada] case class DoubleL[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    override  val self = this
    override type self = DoubleL[k, v, l, r, o]
    override  def apply: apply = NewNode(r.left.key, r.left.value, NewNode(k, v, l, r.left.left, o).apply, NewNode(r.key, r.value, r.left.right, r.right, o).apply, o).apply.asInstanceOf[apply]
    override type apply =        NewNode[r#left#key, r#left#value, NewNode[k, v, l, r#left#left, o]#apply, NewNode[r#key, r#value, r#left#right, r#right, o]#apply, o]#apply
}

private[mada] case class DoubleR[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    override  val self = this
    override type self = DoubleR[k, v, l, r, o]
    override  def apply: apply = NewNode(l.right.key, l.right.value, NewNode(l.key, l.value, l.left, l.right.left, o).apply, NewNode(k, v, l.right.right, r, o).apply, o).apply.asInstanceOf[apply]
    override type apply =        NewNode[l#right#key, l#right#value, NewNode[l#key, l#value, l#left, l#right#left, o]#apply, NewNode[k, v, l#right#right, r, o]#apply, o]#apply
}

