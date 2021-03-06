

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Flatten[+A](_1: Reactive[Sequence[A]]) extends Forwarder[A] {
    override protected val delegate = _1.unsplit(empty)
}
