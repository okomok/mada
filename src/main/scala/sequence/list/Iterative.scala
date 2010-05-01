

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package list


case class AsIterative[A](_1: List[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private var it = _1
        override def isEnd = it.isEmpty
        override def deref = { preDeref; it.head }
        override def increment = { preIncrement; it = it.tail }
    }

    override def isEmpty = _1.isEmpty
}
