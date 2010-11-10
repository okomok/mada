

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Infinite() extends Resource[Unit] {
    @volatile private[this] var isClosed = false
    override protected def closeResource = isClosed = true
    override protected def openResource(f: Unit => Unit) {
        while (!isClosed) {
            f()
        }
    }
}
