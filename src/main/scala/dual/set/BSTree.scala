

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


sealed trait BSTree extends Set {
    type self <: BSTree

     val impl: impl
    type impl <: map.bstree.BSTree
}


private[mada] final case class BSTreeFrom[m <: map.bstree.BSTree](override val impl: m) extends BSTree {
    type self = BSTreeFrom[m]

    override type impl = m

    override  def size: size = impl.size
    override type size = impl#size

    override  def isEmpty: isEmpty = impl.isEmpty
    override type isEmpty = impl#isEmpty

    override  def add[k <: Any](k: k): add[k] = new BSTreeAdd().apply(self, k)
    override type add[k <: Any] = BSTreeAdd#apply[self, k]

    override  def clear: clear = new BSTreeClear().apply(self)
    override type clear = BSTreeClear#apply[self]

    override  def remove[k <: Any](k: k): remove[k] = new BSTreeRemove().apply(self, k)
    override type remove[k <: Any] = BSTreeRemove#apply[self, k]

    override  def contains[k <: Any](k: k): contains[k] = impl.contains(k)
    override type contains[k <: Any] = impl#contains[k]

    override  def toList: toList = impl.keyList
    override type toList = impl#keyList

    override  def undual: undual = impl.undual.keySet
}


@typeInstantiationErrorWorkaround
private[mada] final class BSTreeClear {
     def apply[t <: BSTree](t: t): apply[t] = BSTreeFrom(t.impl.clear)
    type apply[t <: BSTree] = BSTreeFrom[t#impl#clear]
}

private[mada] final class BSTreeAdd {
     def apply[t <: BSTree, k <: Any](t: t, k: k): apply[t, k] = BSTreeFrom(t.impl.put(k, Unit))
    type apply[t <: BSTree, k <: Any] = BSTreeFrom[t#impl#put[k, Unit]]
}

private[mada] final class BSTreeRemove {
     def apply[t <: BSTree, k <: Any](t: t, k: k): apply[t, k] = BSTreeFrom(t.impl.remove(k))
    type apply[t <: BSTree, k <: Any] = BSTreeFrom[t#impl#remove[k]]
}
