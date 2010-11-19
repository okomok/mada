

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Single[+A](_1: A) extends Reactive[A] {
    override def forloop(f: A => Unit, k: => Unit) {
        f(_1)
        k
    }
}
