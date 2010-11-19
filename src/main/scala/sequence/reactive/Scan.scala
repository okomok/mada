

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class ScanLeft[A, B](_1: Reactive[A], _2: B, _3: (B, A) => B) extends Reactive[B] {
    override def close() = _1.close()
    override def forloop(f: B => Unit, k: => Unit) {
        var acc = _2
        f(acc)
        _1 _for { x =>
            acc = _3(acc, x)
            f(acc)
        } _then {
            k
        }
    }
//    override def head = _2
}

private
case class ScanLeft1[A, B >: A](_1: Reactive[A], _3: (B, A) => B) extends Reactive[B] {
    override def close() = _1.close()
    override def forloop(f: B => Unit, k: => Unit) {
        var acc: Option[B] = None
        _1 _for { x =>
            if (acc.isEmpty) {
                acc = Some(x)
            } else {
                acc = Some(_3(acc.get, x))
            }
            f(acc.get)
        } _then {
            k
        }
    }
}
