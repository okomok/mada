

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Merge[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def close() = { _1.close(); _2.close() }
    override def forloop(f: A => Unit, k: => Unit) {
        val _k = IfFirst[Unit => Unit] { _ => () } Else { _ = k }
        val lock = new AnyRef{}
        _1 _for { x =>
            lock.synchronized {
                f(x)
            }
        } _then {
            lock.synchronized {
                _k()
            }
        }

        _2 _for { y =>
            lock.synchronized {
                f(y)
            }
        } _then {
            lock.synchornized {
                _k()
            }
        }
    }
}
