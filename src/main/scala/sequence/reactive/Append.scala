

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Append[A](_1: Reactive[A], _2: util.ByName[Reactive[A]]) extends Resource[A] {
    private var g: A => Unit = null
    override protected def closeResource = {
        _1.close
        if (g ne null) _2().foreach(g)
    }
    override protected def openResource(f: A => Unit) = {
        g = f
        _1.foreach(f)
    }
}
