

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


case class ByName[+R](_1: Function0[R]) extends Function0[R] {
    override def apply = _1()

    def toRunnable(implicit pre: R <:< Unit): Runnable = new Runnable {
        override def run() = pre(apply)
    }
}

object ByName {
    def apply[R](body: => R, o: Overload = ()): ByName[R] = new ByName(() => body)
    implicit def _fromExpr[R](from: => R): ByName[R] = apply(from)
    implicit def _toRunnable(from: ByName[Unit]): Runnable = from.toRunnable
}
