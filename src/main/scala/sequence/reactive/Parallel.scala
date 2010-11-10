

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Parallel() extends Resource[Unit] {
    @volatile private[this] var isClosed = false
    override protected def closeResource = isClosed = true
    override protected def openResource(f: Unit => Unit) {
        val THRESHOLD = 16
        while (!isClosed) {
            try {
                util.Execute {
                    while (!isClosed) {
                        f()
                    }
                }
                return
            } catch {
                case _: java.util.concurrent.RejectedExecutionException => {
                    var i = 0
                    while (!isClosed && i < THRESHOLD) {
                        f()
                        i += 1
                    }
                }
            }
        }
    }
}
