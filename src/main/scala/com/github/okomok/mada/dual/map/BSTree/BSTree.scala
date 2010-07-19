

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


sealed abstract class BSTree extends Map {
    type self <: BSTree

    final override  def asInstanceOfMapBSTree = self
    final override type asInstanceOfMapBSTree = self

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

    final override  def keySet: keySet = set.BSTree(self)
    final override type keySet = set.BSTree[self]

//    final  def equivTo[that <: BSTree, ve <: Equiv](that: that, ve: ve): equivTo[that, ve] = toList.equivTo(that.toList, Product2.eqv(ord, ve))
//    final type equivTo[that <: BSTree, ve <: Equiv] = toList#equivTo[that#toList, Product2.eqv[ord, ve]]
}


final case class Nil[o <: Ordering](override val ord: o) extends BSTree {
    override  val self = this
    override type self = Nil[o]

    override  def size: size = nat.peano.Zero
    override type size = nat.peano.Zero

    override  def key: key = unsupported("dual.map.bstree.Nil.key")
    override type key = unsupported[_]

    override  def value: value = unsupported("dual.map.bstree.Nil.value")
    override type value = unsupported[_]

    override  def left: left = unsupported("dual.map.bstree.Nil.left")
    override type left = unsupported[_]

    override  def right: right = unsupported("dual.map.bstree.Nil.right")
    override type right = unsupported[_]

    override type ord = o

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def get[k <: Any](k: k): get[k] = None
    override type get[k <: Any] = None

    override  def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = Node(k, v, self, self)
    override type put[k <: Any, v <: Any] = Node[k, v, self, self]

    override  def remove[k <: Any](k: k): remove[k] = self
    override type remove[k <: Any] = self

    override  def toList: toList = list.Nil
    override type toList = list.Nil

    override  def keyList: keyList = list.Nil
    override type keyList = list.Nil

    override  def valueList: valueList = list.Nil
    override type valueList = list.Nil

    override  def undual: undual = scala.collection.immutable.Map.empty
}


final case class Node[k <: Any, v <: Any, l <: BSTree, r <: BSTree](
    override val key: k, override val value: v, override val left: l, override val right: r) extends BSTree
{
    Predef.assert(left.ord.undual == right.ord.undual)

    override  val self = this
    override type self = Node[k, v, l, r]

    override  val size: size = (left.size + right.size).increment.asInstanceOf[size]
    override type size = left#size# +[right#size]#increment

    override type key = k
    override type value = v
    override type left = l
    override type right = r

    override  val ord: ord = left.ord
    override type ord = left#ord

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  def get[k <: Any](k: k): get[k] = new NodeGet().apply(self, k)
    override type get[k <: Any] = NodeGet#apply[self, k]

    override  def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = new NodePut().apply(self, k, v)
    override type put[k <: Any, v <: Any] = NodePut#apply[self, k, v]

    override  def remove[k <: Any](k: k): remove[k] = new NodeRemove().apply(self, k)
    override type remove[k <: Any] = NodeRemove#apply[self, k]

    override  def toList: toList = new NodeToList().apply(self)//(right.toList.:::(list.single(Tuple2(key, value))).:::(left.toList)).asInstanceOf[toList]
    override type toList = NodeToList#apply[self]//right#toList# :::[list.single[Tuple2[key, value]]]# :::[left#toList]

    override  def keyList: keyList = new NodeKeyList().apply(self)//(left.keyList ::: list.Cons(key, list.Nil) ::: right.keyList)//.asInstanceOf[keyList]
    override type keyList = NodeKeyList#apply[self]//left#keyList ::: list.Cons[key, list.Nil] ::: right#keyList

    override  def valueList: valueList = new NodeValueList().apply(self)//(left.valueList ::: list.single(value) ::: right.valueList).asInstanceOf[valueList]
    override type valueList = NodeValueList#apply[self]//left#valueList ::: list.single[value] ::: right#valueList

    override  def undual: undual = (left.undual ++ right.undual) + (key -> value)
}
