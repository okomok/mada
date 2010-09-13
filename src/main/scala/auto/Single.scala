

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package auto


private[auto]
case class Single[+A](_1: A) extends Auto[A] {
    override def foreach(f: A => Unit) = f(_1)
}
