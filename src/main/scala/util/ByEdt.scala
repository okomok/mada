

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


/**
 * Runs in the event-dispatch-thread.
 */
case class ByEdt[R](_1: Function0[R]) extends Function0[R] {
    val c = new java.util.concurrent.CountDownLatch(1)
    var r: Either[R, Throwable] = null
    javax.swing.SwingUtilities.invokeLater {
        new Runnable {
            override def run {
                try {
                    r = Left(_1())
                } catch {
                    case t: Throwable => r = Right(t)
                } finally {
                    c.countDown
                }
            }
        }
    }
    private[this] lazy val v = {
        c.await
        r match {
            case Left(r) => r
            case Right(t) => throw t
        }
    }
    override def apply = v
}

object ByEdt {
    def apply[R](body: => R, o: Overload = ()): ByEdt[R] = new ByEdt(() => body)
}
