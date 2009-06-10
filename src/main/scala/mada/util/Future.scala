

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


class Future[+R](body: => R) extends ByLazy[R] {
    val _1: ByLazy[R] = this

    private val f = {
        try {
            parallel(body)
        } catch {
            case _: java.util.concurrent.RejectedExecutionException => byLazy(body)
        }
    }
    override def apply() = f()
}
