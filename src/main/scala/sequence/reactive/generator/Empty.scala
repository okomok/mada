

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


private
case class Empty() extends TrivialGenerator[Nothing] {
    override def isEnd = true
    override protected def generateTo(f: Nothing => Unit) = ()
}
