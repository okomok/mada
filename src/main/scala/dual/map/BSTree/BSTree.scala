

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

    final override  def keySet: keySet = set.BSTreeFrom(self)
    final override type keySet         = set.BSTreeFrom[self]

    final override  def clear: clear    = Nil(self.ord)
    private type _clear[self <: BSTree] = Nil[self#ord]
    final override type clear = _clear[self]
}


final case class Nil[o <: Ordering](override val ord: o) extends BSTree {
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

    override  def toSeq: toSeq = seq.Nil
    override type toSeq        = seq.Nil

    override  def keySeq: keySeq = seq.Nil
    override type keySeq         = seq.Nil

    override  def valueSeq: valueSeq = seq.Nil
    override type valueSeq           = seq.Nil

    override  def undual: undual = scala.collection.immutable.Map.empty
}


final case class Node[k <: Any, v <: Any, l <: BSTree, r <: BSTree](
    override val key: k, override val value: v, override val left: l, override val right: r) extends BSTree
{
    Predef.assert(left.ord.undual == right.ord.undual)

    type self = Node[k, v, l, r]

    override  val size: size = (left.size  + right.size).increment.asInstanceOf[size]
    override type size       =  left#size# +[right#size]#increment

    override type key = k
    override type value = v
    override type left = l
    override type right = r

    override  val ord: ord = left.ord
    override type ord      = left#ord

    override  def isEmpty: isEmpty = `false`
    override type isEmpty          = `false`

    override  def get[k <: Any](k: k): get[k] = new NodeGet().apply(self, k)
    override type get[k <: Any] = NodeGet#apply[self, k]

    override  def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = new NodePut().apply(self, k, v)
    override type put[k <: Any, v <: Any] = NodePut#apply[self, k, v]

    override  def remove[k <: Any](k: k): remove[k] = new NodeRemove().apply(self, k)
    override type remove[k <: Any] = NodeRemove#apply[self, k]

    override  def toSeq: toSeq          = (self.left.toSeq ++ seq.single(Tuple2(self.key, self.value)) ++ self.right.toSeq).asInstanceOf[toSeq]
    private type _toSeq[self <: BSTree] =  self#left#toSeq ++ seq.single[Tuple2[self#key, self#value]] ++ self#right#toSeq
    override type toSeq = _toSeq[self]

    override  def keySeq: keySeq        =  (self.left.keySeq ++ seq.single(self.key) ++ self.right.keySeq).asInstanceOf[keySeq]
    private type _keySeq[self <: BSTree] =  self#left#keySeq ++ seq.single[self#key] ++ self#right#keySeq
    override type keySeq = _keySeq[self]

    override  def valueSeq: valueSeq      =  (self.left.valueSeq ++ seq.single(self.value) ++ self.right.valueSeq).asInstanceOf[valueSeq]
    private type _valueSeq[self <: BSTree] =  self#left#valueSeq ++ seq.single[self#value] ++ self#right#valueSeq
    override type valueSeq = _valueSeq[self]

    override  def undual: undual = (left.undual + (key.undual -> value.undual)) ++ right.undual
}
