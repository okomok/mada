

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Unique[+A](_1: Reactive[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)
    override def unique: Reactive[A] = this // unique.unique fusion
}

private
case class UniqueBy[A](_1: Reactive[A], _2: (A, A) => Boolean) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        var prev: Option[A] = None
        _1 _for { x =>
            if (prev.isEmpty || !_2(prev.get, x)) {
                f(x)
            }
            prev = Some(x)
        } _then {
            k
        }
    }
}
