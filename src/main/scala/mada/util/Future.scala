

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


class Future[+R](body: => R) extends Function0[R] {
    val _1: Function0[R] = this

    private val f = {
        try {
            parallel(body)
        } catch {
            case _: java.util.concurrent.RejectedExecutionException => byLazy(body)
        }
    }
    override def apply() = f()
}
