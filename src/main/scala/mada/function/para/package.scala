

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.function


import java.util.concurrent._


package object para {

    val POOL_SIZE = 2 * java.lang.Runtime.getRuntime.availableProcessors

    // Interdependent tasks need unbounded pools to avoid starvation deadlock.
    // (scala.actors.Future doesn't support dependent tasks.)
    val executor =
        new ThreadPoolExecutor(0, POOL_SIZE, 60L, TimeUnit.SECONDS, new SynchronousQueue[Runnable]())
        // Executors.newCachedThreadPool()

}
