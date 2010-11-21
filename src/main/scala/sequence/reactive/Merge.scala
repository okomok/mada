

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Merge[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def close() = { _1.close(); _2.close() }
    override def forloop(f: A => Unit, k: Exit => Unit) {
        val _k = IfFirst[Exit] { _ => () } Else { q => k(q) }
        val lock = new AnyRef{}

        _1 _for { x =>
            lock.synchronized {
                f(x)
            }
        } _then { q =>
            lock.synchronized {
                _k(q)
            }
        }

        _2 _for { y =>
            lock.synchronized {
                f(y)
            }
        } _then { q =>
            lock.synchronized {
                _k(q)
            }
        }
    }
}
