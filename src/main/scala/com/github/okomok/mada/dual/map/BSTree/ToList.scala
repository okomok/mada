

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


@compilerWorkaround("2.8.0") // Scalac needs a new class to avoid "java.lang.Error: x in class Cons cannot be instantiated from _72.type".
private[mada] final class NodeToList {
     def apply[m <: BSTree](m: m): apply[m] = (m.left.toList ::: list.single(Tuple2(m.key, m.value)) ::: m.right.toList).asInstanceOf[apply[m]]
    type apply[m <: BSTree] = m#left#toList ::: list.single[Tuple2[m#key, m#value]] ::: m#right#toList
}

private[mada] final class NodeKeyList {
     def apply[m <: BSTree](m: m): apply[m] = (m.left.keyList ::: list.single(m.key) ::: m.right.keyList).asInstanceOf[apply[m]]
    type apply[m <: BSTree] = m#left#keyList ::: list.single[m#key] ::: m#right#keyList
}

private[mada] final class NodeValueList {
     def apply[m <: BSTree](m: m): apply[m] = (m.left.valueList ::: list.single(m.value) ::: m.right.valueList).asInstanceOf[apply[m]]
    type apply[m <: BSTree] = m#left#valueList ::: list.single[m#value] ::: m#right#valueList
}
