

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


// import java.util.concurrent
import util.Future


private
object ParallelEach {
    def apply[A](_1: Vector[A], _2: A => Unit, _3: Int) {
        assert(!IsParallel(_1))

        if (_3 == 1) {
            _1.map{ e => Future(_2(e)) }.force.foreach{ u => u() }
        } else {
            _1.divide(_3).map{ w => Future(w.foreach(_2)) }.
                force. // start tasks.
                    foreach{ u => u() } // join all.
/*
            // slightly faster...
            val xss = _1.divide(_3)
            val c = new concurrent.CountDownLatch(xss.size)
            xss.foreach { xs =>
                def f = { xs.foreach(_2); c.countDown }
                try {
                    util.Parallel(f)
                } catch {
                    case _: concurrent.RejectedExecutionException => f
                }
            }
            c.await
*/
        }
    }
}
