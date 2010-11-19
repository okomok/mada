

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Fork[A](_1: Reactive[A], _2: Reactive[A] => Reactive[_]) extends Forwarder[A] {
    override protected lazy val delegate = {
        val (xs, ys) = _1.duplicate
        _2(xs).start()
        ys
    }
}
