

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Single[+A](_1: A) extends Reactive[A] {
    override def foreach(f: A => Unit) = f(_1)
}
