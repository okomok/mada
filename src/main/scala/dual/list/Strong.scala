

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


/**
 * Sequence-derivation helper to work around suboptimal `nsc.symtab.Types.Type.isGround`.
 * (See "C++ Template Metaprogramming" 5.10 and C.3.7)
 */
abstract class Strong[ys <: List](ys: ys) extends AbstractList {
    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty          = ys#isEmpty

    override  def head: head = ys.head
    override type head       = ys#head

    override  def tail: tail = ys.tail
    override type tail       = ys#tail
}

/*
// This is probably worse than above, accessing delegate type-expression earlier.
abstract class Strong[ys <: List](final override protected val delegate: ys) extends TrivialForwarder {
    final override protected type delegate = ys

    final override  def asList: asList = self
    final override type asList         = self
}
*/
