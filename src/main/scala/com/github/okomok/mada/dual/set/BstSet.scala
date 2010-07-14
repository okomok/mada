

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[mada] final case class BstSet[m <: Map](private val m: m) extends Set {
    override  val self = this
    override type self = BstSet[m]

    override  def size: size = m.size
    override type size = m#size

    override  def isEmpty: isEmpty = m.isEmpty
    override type isEmpty = m#isEmpty

    override  def add[k <: Any](k: k): add[k] = BstSet(m.put(k, Unit))
    override type add[k <: Any] = BstSet[m#put[k, Unit]]

    override  def remove[k <: Any](k: k): remove[k] = BstSet(m.remove(k))
    override type remove[k <: Any] = BstSet[m#remove[k]]

    override  def contains[k <: Any](k: k): contains[k] = m.contains(k)
    override type contains[k <: Any] = m#contains[k]

    override  def undual: undual = scala.collection.immutable.Set.empty + m.undual.keySet
}
