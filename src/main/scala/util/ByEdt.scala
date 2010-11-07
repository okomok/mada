

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


case class ByEdt[R](_1: Function0[R]) extends Function0[R] {
    val c = new java.util.concurrent.CountDownLatch(1)
    var r: Either[R, Throwable] = null
    javax.swing.SwingUtilities.invokeLater {
        new Runnable {
            override def run {
                try {
                    r = Left(_1())
                } catch {
                    case t => r = Right(t)
                }
                c.countDown
            }
        }
    }
    private lazy val _apply = {
        c.await
        r match {
            case Left(r) => r
            case Right(t) => throw t
        }
    }
    override def apply = _apply
}

object ByEdt {
    def apply[R](body: => R, dummy: Unit = ()): ByEdt[R] = new ByEdt(() => body)
}
