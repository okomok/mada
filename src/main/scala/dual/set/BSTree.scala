

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[dual]
final case class BSTree[m <: map.bstree.BSTree](impl: m) extends AbstractSet {
    type self = BSTree[m]

    type impl = m

    override  def size: size = impl.size
    override type size       = impl#size

    override  def isEmpty: isEmpty = impl.isEmpty
    override type isEmpty          = impl#isEmpty

    override  def add[k <: Any](k: k): add[k] = BSTree(self.impl.put(k, Unit))
    override type add[k <: Any]               = BSTree[self#impl#put[k, Unit]]

    override  def clear: clear = BSTree(self.impl.clear)
    override type clear        = BSTree[self#impl#clear]

    override  def remove[k <: Any](k: k): remove[k] = BSTree(self.impl.remove(k))
    override type remove[k <: Any]                  = BSTree[self#impl#remove[k]]

    override  def contains[k <: Any](k: k): contains[k] = impl.contains(k)
    override type contains[k <: Any]                    = impl#contains[k]

    override  def toList: toList = self.impl.keyList
    override type toList         = self#impl#keyList

    override  def undual: undual = impl.undual.keySet
}
