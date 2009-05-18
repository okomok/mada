

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


trait Forwarder[A] extends Stack[A] with any.Forwarder {
    override protected def delegate: Stack[A]

    protected def beforeForward[B](that: Stack[B]): Stack[B] = that

    override def push(e: A): Unit = beforeForward(delegate).push(e)
    override def pop: A = beforeForward(delegate).pop
    override def peek: A = beforeForward(delegate).peek
    override def isEmpty: Boolean = beforeForward(delegate).isEmpty
    override def size: Int = beforeForward(delegate).size
    override def clear: Unit = beforeForward(delegate).clear

    override def toString: String = beforeForward(delegate).toString
}
