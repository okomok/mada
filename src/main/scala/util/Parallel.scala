

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package util


import java.util.concurrent


case class Parallel[R](_1: Function0[R]) extends Function0[R] {
    private val u = {
        val c = new concurrent.Callable[R] { override def call() = _1() }
        Parallels.executor.synchronized { Parallels.executor.submit(c) }
    }
    override def apply() = {
        try {
            u.get
        } catch {
            case e: concurrent.ExecutionException => throw e.getCause
        }
    }
}


object Parallels {
    import concurrent._

    val poolSize: Int = 2 * java.lang.Runtime.getRuntime.availableProcessors

    // Interdependent tasks need unbounded pools to avoid starvation deadlock.
    // (scala.actors.Future doesn't support such tasks.)
    val executor: ThreadPoolExecutor = {
        val ex = new ThreadPoolExecutor(0, poolSize, 60L, TimeUnit.SECONDS, new SynchronousQueue[Runnable])
        // Executors.newCachedThreadPool
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
