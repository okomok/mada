

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.function


import java.util.concurrent
import parallels.executor


class Parallel[R](body: => R) extends Function0[R] {
    val _1: Function0[R] = this

    private val u = {
        val c = new concurrent.Callable[R] { override def call() = body }
        executor.synchronized { executor.submit(c) }
    }
    override def apply() = {
        try {
            u.get
        } catch {
            case e: concurrent.ExecutionException => throw e.getCause
        }
    }
}
