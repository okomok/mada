

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Unique[+A](_1: Reactive[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)
    override def unique: Reactive[A] = this // unique-unique fusion
}

private
case class UniqueBy[A](_1: Reactive[A], _2: (A, A) => Boolean) extends TransformAdapter[A] {
    override def underlying = _1
    override def foreach(f: A => Unit) = {
        var prev: Option[A] = None
        for (x <- _1) {
            if (prev.isEmpty || !_2(prev.get, x)) {
                f(x)
            }
            prev = Some(x)
        }
    }
}
