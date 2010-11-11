

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


import java.util.concurrent.Callable


case class ByName[+R](_1: Function0[R]) extends Function0[R] {
    override def apply = _1()

    def toRunnable: Runnable = new Runnable {
        override def run = apply
    }

    def toCallable[S](implicit pre: R <:< S): Callable[S] = new Callable[S] {
        override def call: S = pre(apply)
    }
}

object ByName extends Strategy {
    override def install[R](to: Function0[R]): Function0[R] = new ByName(to)
    def apply[R](body: => R, o: AsFunction = ()): ByName[R] = new ByName(() => body)
    implicit def _fromExpr[R](from: => R): ByName[R] = apply(from)
    implicit def _toRunnable[R](from: ByName[R]): Runnable = from.toRunnable
    implicit def _toCallable[R](from: ByName[R]): Callable[R] = from.toCallable
}
