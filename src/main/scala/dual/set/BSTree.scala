

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


sealed trait BSTree extends AbstractSet {
    type self <: BSTree

     def impl: impl
    type impl <: map.bstree.BSTree
}


private[dual]
final case class BSTreeFrom[m <: map.bstree.BSTree](override val impl: m) extends BSTree {
    type self = BSTreeFrom[m]

    override type impl = m

    override  def size: size = impl.size
    override type size       = impl#size

    override  def isEmpty: isEmpty = impl.isEmpty
    override type isEmpty          = impl#isEmpty

    override  def add[k <: Any](k: k): add[k] = BSTreeFrom(self.impl.put(k, Unit))
    override type add[k <: Any]               = BSTreeFrom[self#impl#put[k, Unit]]

    override  def clear: clear = BSTreeFrom(self.impl.clear)
    override type clear        = BSTreeFrom[self#impl#clear]

    override  def remove[k <: Any](k: k): remove[k] = BSTreeFrom(self.impl.remove(k))
    override type remove[k <: Any]                  = BSTreeFrom[self#impl#remove[k]]

    override  def contains[k <: Any](k: k): contains[k] = impl.contains(k)
    override type contains[k <: Any]                    = impl#contains[k]

    override  def toList: toList = self.impl.keyList
    override type toList         = self#impl#keyList

    override  def undual: undual = impl.undual.keySet
}
