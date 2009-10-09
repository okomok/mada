

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package stack


trait Forwarder[A] extends Stack[A] with util.Forwarder {
    override protected def delegate: Stack[A]

    override def push(e: A): Unit = delegate.push(e)
    override def pop: A = delegate.pop
    override def peek: A = delegate.peek
    override def isEmpty: Boolean = delegate.isEmpty
    override def size: Int = delegate.size
    override def clear: Unit = delegate.clear

    override def toSome: ToSome[A] = delegate.toSome
}
