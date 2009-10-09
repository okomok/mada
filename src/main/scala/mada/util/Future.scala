

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package util


case class Future[+R](_1: Function0[R]) extends Function0[R] {
    private val f = {
        try {
            Parallel(_1)
        } catch {
            case _: java.util.concurrent.RejectedExecutionException => ByLazy(_1)
        }
    }
    override def apply = f()
}
