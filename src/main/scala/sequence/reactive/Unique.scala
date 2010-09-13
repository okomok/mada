

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Unique[+A](_1: Reactive[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)
    override def unique: Reactive[A] = this // unique-unique fusion
}

private[reactive]
case class UniqueBy[A](_1: Reactive[A], _2: (A, A) => Boolean) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var p: Option[A] = None
        for (x <- _1) {
            if (p.isEmpty || !_2(p.get, x)) {
                f(x)
            }
            p = Some(x)
        }
    }
}
