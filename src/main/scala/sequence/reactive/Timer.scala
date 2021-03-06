

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.TimerTask


case class Schedule(scheduler: TimerTask => Unit) extends NoEndResource[Unit] {
    private[this] var l: TimerTask = null
    override protected def closeResource() = l.cancel()
    override protected def openResource(f: Unit => Unit) {
        l = new TimerTask {
            override def run() = f()
        }
        scheduler(l)
    }
}
