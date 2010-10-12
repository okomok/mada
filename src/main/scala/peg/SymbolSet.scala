

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
class TheSymbolSet[A](tree: TSTree[A, Unit]) extends Peg[A] with scala.collection.mutable.Set[sequence.Vector[A]] {

// Peg
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        tree.parse(v, start, end) match {
            case Some((_, cur)) => cur
            case _ => FAILURE
        }
    }

// scala.collection.Set
    override def iterator = tree.iterator.map{ n => n._1 }

    override def contains(v: sequence.Vector[A]) = tree.containsKey(v)
    override def hashCode = tree.hashCode
    override def isEmpty = tree.isEmpty
    override def size = tree.size
    override def stringPrefix = "SymbolSet"

    override def +=(v: sequence.Vector[A]) = { tree.put(v, ()); this }
    override def -=(v: sequence.Vector[A]) = { tree.remove(v); this }
    override def clear = tree.clear
    override def clone: SymbolSet[A] = new TheSymbolSet(tree.clone)

}
