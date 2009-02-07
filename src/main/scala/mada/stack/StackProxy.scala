

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


/**
 * Implements a proxy for stack objects.
 */
trait StackProxy[A] extends Stack[A] with Proxies.ProxyOf[Stack[A]] {
    override def push(e: A) = self.push(e)
    override def pop: A = self.pop
    override def peek: A = self.peek
    override def isEmpty: Boolean = self.isEmpty
    override def size: Int = self.size
    override def clear: Unit = self.clear
}
