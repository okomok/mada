

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


import java.util.concurrent


/**
 * Runs (possibly) in the thread-pool.
 * If the thread-pool is full, _2 determines the evaluation strategy.
 */
case class Parallel[+R](_1: Function0[R], _2: Strategy) extends Function0[R] {
    private[this] val f = {
        try {
            new Parallel.Execute(_1)
        } catch {
            case _: concurrent.RejectedExecutionException => _2.install(_1)
        }
    }
    override def apply = f()
}


object Parallel {
    import concurrent._

    def apply[R](body: => R): Parallel[R] = new Parallel(() => body, ByReject)
    def apply[R](body: => R, stg: Strategy, o: util.Overload = ()): Parallel[R] = new Parallel(() => body, stg)

    val poolSize: Int = 2 * java.lang.Runtime.getRuntime.availableProcessors
    private val minPoolSize: Int = java.lang.Math.min(4, poolSize)

    // A task which has internal dependencies needs direct-handoffs(SynchronousQueue).
    // (scala.actors.Future doesn't support such tasks.)
    val executor: ThreadPoolExecutor = {
        val ex = new ThreadPoolExecutor(minPoolSize, poolSize, 60L, TimeUnit.SECONDS, new SynchronousQueue[Runnable])
        ex.setThreadFactory(new DaemonThreadFactory(ex.getThreadFactory))
        ex
    }

    private
    class DaemonThreadFactory(underlying: ThreadFactory) extends ThreadFactory {
        override def newThread(r: Runnable) = {
            val t = underlying.newThread(r)
            t.setDaemon(true)
            t
        }
    }

    private
    class Execute[R](_1: Function0[R]) extends Function0[R] {
        private[this] val u = {
            val c = new Callable[R] { override def call() = _1() }
            executor.synchronized { executor.submit(c) }
        }
        override def apply = {
            try {
                u.get
            } catch {
                case e: ExecutionException => throw e.getCause
            }
        }
    }
}
