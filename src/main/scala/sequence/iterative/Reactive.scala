

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


import reactive.{Exit, End, Thrown}


private[iterative]
case class AsReactive[+A](_1: Iterative[A]) extends Reactive[A] {
    override def forloop(f: A => Unit, k: Exit => Unit) = {
        try {
            _1.foreach(f)
        } catch {
            case t: Throwable => {
                k(Thrown(t))
                throw t
            }
        }
        k(End)
    }
}
