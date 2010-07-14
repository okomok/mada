

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


object Map extends Common


/**
 * The dual Option
 */
sealed abstract class Map extends Any {
    type self <: Map

    final override  def asInstanceOfMap = self
    final override type asInstanceOfMap = self

     def size: size
    type size <: nat.Peano

     def key: key
    type key <: Any

     def value: value
    type value <: Any

     def left: left
    type left <: Map

     def right: right
    type right <: Map

     def ord: ord
    type ord <: Ordering

     def isEmpty: isEmpty
    type isEmpty <: Boolean

     def get[k <: Any](k: k): get[k]
    type get[k <: Any] <: Option

     def put[k <: Any, v <: Any](k: k, v: v): put[k, v]
    type put[k <: Any, v <: Any] <: Map

     def remove[k <: Any](k: k): remove[k]
    type remove[k <: Any] <: Map

    final override type undual = scala.collection.immutable.Map[_, _]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Map]
}


final case class Nil[o <: Ordering](override val ord: o) extends Map {
    override  val self = this
    override type self = Nil[o]

    override  def size: size = nat.peano.Zero
    override type size = nat.peano.Zero

    override  def key: key = unsupported("dual.map.Nil.key")
    override type key = unsupported[_]

    override  def value: value = unsupported("dual.map.Nil.value")
    override type value = unsupported[_]

    override  def left: left = unsupported("dual.map.Nil.left")
    override type left = unsupported[_]

    override  def right: right = unsupported("dual.map.Nil.right")
    override type right = unsupported[_]

    override type ord = o

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def get[k <: Any](k: k): get[k] = None
    override type get[k <: Any] = None

    override  def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = single(k, v, ord)
    override type put[k <: Any, v <: Any] = single[k, v, ord]

    override  def remove[k <: Any](k: k): remove[k] = self
    override type remove[k <: Any] = self

    override  def undual: undual = scala.collection.immutable.Map.empty
}


final case class Node[n <: nat.Peano, k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](
    override val size: n,
    override val key: k, override val value: v,
    override val left: l, override val right: r,
    override val ord: o) extends Map
{
    override  val self = this
    override type self = Node[n, k, v, l, r, o]

    override type size = n
    override type key = k
    override type value = v
    override type left = l
    override type right = r
    override type ord = o

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  def get[k <: Any](k: k): get[k] = new NodeGet().apply(self, k)
    override type get[k <: Any] = NodeGet#apply[self, k]

    override  def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = new NodePut().apply(self, k, v)
    override type put[k <: Any, v <: Any] = NodePut#apply[self, k, v]

    override  def remove[k <: Any](k: k): remove[k] = new NodeRemove().apply(self, k)
    override type remove[k <: Any] = NodeRemove#apply[self, k]

    override  def undual: undual = (left.undual ++ right.undual) + (key -> value)
}


private[mada] object _Map {
}
