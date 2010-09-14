

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Break[+A](_1: Reactive[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = _1.start
    override def break = _1.break // break-break fusion
}
