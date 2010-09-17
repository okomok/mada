

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


case class SinglePassException[A](_1: Reactive[A]) extends UnsupportedOperationException("single pass only")


private
case class SinglePass[+A](_1: Reactive[A]) extends Reactive[A] {
    private val t = new IfFirst[A => Unit](_1.foreach(_), _ => throw SinglePassException(_1))
    override def foreach(f: A => Unit) = t(f)
}
