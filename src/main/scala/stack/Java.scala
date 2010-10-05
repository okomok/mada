

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package stack


private
case class FromJDeque[A](_1: java.util.Deque[A]) extends Stack[A] {
    override def push(e: A) = _1.push(e)
    override def pop = _1.pop
    override def peek = _1.peek
    override def size = _1.size
    override def isEmpty = _1.isEmpty
    override def clear = _1.clear
}
