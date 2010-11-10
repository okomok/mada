

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


import java.util.concurrent


/**
 * Runs in the thread-pool.
 * If submission fails, RejectedExecutionException is thrown.
 * (It actually fails very often as the thread pool size is small.)
 */
case class Execute[R](_1: Function0[R]) extends Function0[R] {
    private[this] val u = {
        val c = new concurrent.Callable[R] { override def call() = _1() }
        Execute.executor.synchronized { Execute.executor.submit(c) }
    }
    override def apply = {
        try {
            u.get
        } catch {
            case e: concurrent.ExecutionException => throw e.getCause
        }
    }
}

object Execute {
    import concurrent._

    def apply[R](body: => R, o: Overload = ()): Execute[R] = new Execute(() => body)

    val poolSize: Int = 2 * java.lang.Runtime.getRuntime.availableProcessors

    // A task which has internal dependencies needs direct-handoffs(SynchronousQueue).
    // (scala.actors.Future doesn't support such tasks.)
    val executor: ThreadPoolExecutor = {
        val ex = new ThreadPoolExecutor(0, poolSize, 60L, TimeUnit.SECONDS, new SynchronousQueue[Runnable])
        ex.setThreadFactory(new DaemonThreadFactory(ex.getThreadFactory))
        ex
    }

    private class DaemonThreadFactory(underlying: ThreadFactory) extends ThreadFactory {
        override def newThread(r: Runnable) = {
            val t = underlying.newThread(r)
            t.setDaemon(true)
            t
        }
    }
}
