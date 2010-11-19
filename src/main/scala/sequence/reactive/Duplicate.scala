

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import eval.ByName


private
case class Duplicate[A](_1: Reactive[A]) extends Reactive[A] {
    private[this] var _f: A => Unit = null
    private[this] var _k: ByName[Unit] = null
    private[this] val _close = {
        IfFirst[Unit] {
            _ => ()
        } Else {
            _ => _1.close()
        }
    }
    private[this] val _foreach = {
        IfFirst[(A => Unit, ByName[Unit])] {
            case (f, k) => _f = f; _k = k
        } Else {
            case (f, k) => _1.react(_f).onEnd(_k).forloop(f, k)
        }
    }
    override def close() = _close()
    override def forloop(f: A => Unit, k: => Unit) = _foreach(f, ByName(k))
}
