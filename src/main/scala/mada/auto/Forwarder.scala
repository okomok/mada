

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package auto


trait Forwarder[+A] extends Auto[A] with util.Forwarder {
    override protected def delegate: Auto[A]

    override def get: A = delegate.get
    override def begin: Unit = delegate.begin
    override def end: Unit = delegate.end
    override def usedBy[B](f: A => B): B = delegate.usedBy(f)
}
