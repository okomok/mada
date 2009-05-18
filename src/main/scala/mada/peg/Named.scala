

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Named {
    def apply[A](p: Peg[A], name: String): Peg[A] = new NamedPeg(p, name)
}

private[mada] class NamedPeg[A](override val delegate: Peg[A], name: String) extends Forwarder[A] {
    override def toString = name
}
