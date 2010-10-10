

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.{Timer, TimerTask}


object Timers {

    case class Task(schedule: TimerTask => Unit) extends Resource[Unit] {
        private var l: TimerTask = null
        override protected def closeResource = l.cancel()
        override protected def openResource(f: Unit => Unit) = {
            l = new TimerTask {
                override def run() = f()
            }
            schedule(l)
        }
    }

}
