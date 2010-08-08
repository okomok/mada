

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.

// Copyright Daan Leijen 2002.


package com.github.okomok.mada
package dual; package map; package bstree


sealed abstract class BSTree extends AbstractMap {
    type self <: BSTree
    type asInstanceOfMapBSTree = self

    override type put[k <: Any, v <: Any] <: BSTree
    override type remove[k <: Any] <: BSTree
    override type clear <: BSTree

     def key: key
    type key <: Any

     def value: value
    type value <: Any

     def left: left
    type left <: BSTree

     def right: right
    type right <: BSTree

     def ord: ord
    type ord <: Ordering
}


sealed abstract class AbstractBSTree extends BSTree {
    final override  def keySet: keySet   = set.BSTreeFrom(self)
    private type _keySet[self <: BSTree] = set.BSTreeFrom[self]
    final override type keySet = _keySet[self]

    final override  def clear: clear    = Nil(self.ord)
    private type _clear[self <: BSTree] = Nil[self#ord]
    final override type clear = _clear[self]
}


final case class Nil[o <: Ordering](override val ord: o) extends AbstractBSTree {
    type self = Nil[o]

    override  def size: size = nat.dense._0
    override type size       = nat.dense._0

    override  def key: key = unsupported("map.bstree.Nil.key")
    override type key      = unsupported[_]

    override  def value: value = unsupported("map.bstree.Nil.value")
    override type value        = unsupported[_]

    override  def left: left = unsupported("map.bstree.Nil.left")
    override type left       = unsupported[_]

    override  def right: right = unsupported("map.bstree.Nil.right")
    override type right        = unsupported[_]

    override type ord = o

    override  def isEmpty: isEmpty = `true`
    override type isEmpty          = `true`

    override  def get[k <: Any](k: k): get[k] = None
    override type get[k <: Any]               = None

    override  def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = Node(k, v, self, self)
    private type _put[self <: BSTree, k <: Any, v <: Any]        = Node[k, v, self, self]
    override type put[k <: Any, v <: Any] = _put[self, k, v]

    override  def remove[k <: Any](k: k): remove[k] = self
    override type remove[k <: Any]                  = self

    override  def toList: toList = list.Nil
    override type toList         = list.Nil

    override  def keyList: keyList = list.Nil
    override type keyList          = list.Nil

    override  def valueList: valueList = list.Nil
    override type valueList            = list.Nil

    override  def undual: undual = scala.collection.immutable.Map.empty
}


final case class Node[k <: Any, v <: Any, l <: BSTree, r <: BSTree](
    override val key: k, override val value: v, override val left: l, override val right: r) extends AbstractBSTree
{
    Predef.assert(left.ord.undual == right.ord.undual)

    type self = Node[k, v, l, r]

    override  val size: size = left.size.plus(right.size).increment.asInstanceOf[size]
    override type size       = left#size#plus[right#size]#increment

    override type key = k
    override type value = v
    override type left = l
    override type right = r

    override  val ord: ord = left.ord
    override type ord      = left#ord

    override  def isEmpty: isEmpty = `false`
    override type isEmpty          = `false`

    override  def get[k <: Any](k: k): get[k] = NodeGet.apply(self, k)
    override type get[k <: Any]               = NodeGet.apply[self, k]

    override  def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = NodePut.apply(self, k, v)
    override type put[k <: Any, v <: Any]                        = NodePut.apply[self, k, v]

    override  def remove[k <: Any](k: k): remove[k] = NodeRemove.apply(self, k)
    override type remove[k <: Any]                  = NodeRemove.apply[self, k]

    override  def toList: toList         = self.left.toList.append(Tuple2(self.key, self.value) :: self.right.toList).asInstanceOf[toList]
    private type _toList[self <: BSTree] = self#left#toList#append[Tuple2[self#key, self#value] :: self#right#toList]
    override type toList = _toList[self]

    override  def keyList: keyList        = self.left.keyList.append(self.key :: self.right.keyList).asInstanceOf[keyList]
    private type _keyList[self <: BSTree] = self#left#keyList#append[self#key :: self#right#keyList]
    override type keyList = _keyList[self]

    override  def valueList: valueList      = self.left.valueList.append(self.value :: self.right.valueList).asInstanceOf[valueList]
    private type _valueList[self <: BSTree] = self#left#valueList#append[self#value :: self#right#valueList]
    override type valueList = _valueList[self]

    override  def undual: undual = (left.undual + (key.undual -> value.undual)) ++ right.undual
}
