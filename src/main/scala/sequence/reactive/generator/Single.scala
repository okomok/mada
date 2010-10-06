

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


private
case class Single[A](_1: A) extends TrivialGenerator[A] {
    private var x: Option[A] = Some(_1)
    override def isEmpty = synchronized { x.isEmpty }
    override protected def generateTo(f: A => Unit) = synchronized {
        if (!x.isEmpty) {
            val now = x.get
            x = None
            f(now)
        }
    }
}
