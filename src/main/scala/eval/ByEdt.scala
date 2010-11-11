

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


/**
 * Runs in the event-dispatch-thread.
 */
case class ByEdt[R](_1: Function0[R]) extends Function0[R] {
    private[this] val f = Invoke(_1, r => javax.swing.SwingUtilities.invokeLater(r))
    override def apply = f()
}

object ByEdt extends Strategy {
    override def install[R](to: Function0[R]): Function0[R] = new ByEdt(to)
    def apply[R](body: => R, o: util.Overload = ()): ByEdt[R] = new ByEdt(() => body)
}