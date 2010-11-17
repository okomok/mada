

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class ZipWithIndex[+A](_1: Reactive[A]) extends Reactive[(A, Int)] {
    override def close() = _1.close()
    override def foreach(f: Tuple2[A, Int] => Unit) {
        var i = 0
        for (x <- _1) {
            f(x, i)
            i += 1
        }
    }
}
