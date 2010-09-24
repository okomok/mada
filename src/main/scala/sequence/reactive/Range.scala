

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Range(_1: Int, _2: Int, _3: util.ByName[Unit] = util.byName(())) extends GeneratorOnce[Int] {
    private var cur = _1
    override def generateOne = {
        if (cur != _2) {
            out(cur)
            cur += 1
            if (cur == _2) {
                _3()
            }
        }
    }
    override val head = _1
}
