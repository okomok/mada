

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Indices[+A](_1: Reactive[A]) extends Forwarder[Int] {
    override protected val delegate = _1.generate(vector.range(0, ()))
}
