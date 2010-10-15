

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Branch[A](_1: Reactive[A]) extends Reactive[A] {
    private var g: A => Unit = null
    private val _close = IfFirst[Unit] { _ => () } Else { _ => _1.close }
    private val _foreach = IfFirst[A => Unit] { f => g = f } Else { f => _1.reactTotal(g).foreach(f) }
    override def close = _close()
    override def foreach(f: A => Unit) = _foreach(f)
}
