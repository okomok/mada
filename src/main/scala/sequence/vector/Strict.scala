

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


// Hmm...

private
case class Strict[A](_1: Vector[A]) extends Forwarder[A] {
    override protected val delegate = _1.mix(Mixin.force)

    // override def filter = delegate.mutatingFilter
}
