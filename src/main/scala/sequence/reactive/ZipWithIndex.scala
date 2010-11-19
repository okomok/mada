

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class ZipWithIndex[+A](_1: Reactive[A]) extends Reactive[(A, Int)] {
    override def close() = _1.close()
    override def forloop(f: Tuple2[A, Int] => Unit, k: => Unit) {
        var i = 0
        _1 _for { x =>
            f(x, i)
            i += 1
        } _then {
            k
        }
    }
}
