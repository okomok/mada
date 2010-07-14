

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


object Map


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

    final  def get[k <: Any](k: k): get[k] = new Get().apply(self, k)
    final type get[k <: Any] = Get#apply[self, k]

    final override type undual = scala.collection.immutable.Map[_, _]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Map]
}


sealed abstract class Nil extends Map {
    override  val self = this
    override type self = Nil

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

    override  def ord: ord = unsupported("dual.map.Nil.ord")
    override type ord = unsupported[_]

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

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

    override  def undual: undual = (left.undual ++ right.undual) + (key -> value)
}


private[mada] object _Map {
    val Nil = new Nil{}
}
