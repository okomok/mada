

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


trait Forwarder[+A] extends List[A] with iterative.SequenceForwarder[A] {
    protected def delegate: List[A]

    override def isEmpty = delegate.isEmpty
    override def head = delegate.head
    override def tail = delegate.tail

    // TODO:

}
