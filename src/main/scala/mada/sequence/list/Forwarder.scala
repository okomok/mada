

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


trait Forwarder[+A] extends List[A] with SequenceForwarder[A] {
    protected def delegate: List[A]

    override def isNil = delegate.isNil
    override def head = delegate.head
    override def tail = delegate.tail

    // TODO:

}
