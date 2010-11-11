

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


// import java.util.concurrent
import eval.Future


private
object ParallelEach {
    def apply[A](_1: Vector[A], _2: A => Unit, _3: Int) {
        assert(!IsParallel(_1))

        if (_3 == 1) {
            JoinFutures(_1.map{ e => Future(_2(e)) }.force)
        } else {
            JoinFutures(_1.divide(_3).map{ w => Future(w.foreach(_2)) }.force)
/*
            // more scalable?
            val xss = _1.divide(_3)
            val c = new concurrent.CountDownLatch(xss.size)
            xss.foreach { xs =>
                eval.Parallel.or(eval.ByStrict) {
                    try {
                        xs.foreach(_2)
                    } finally {
                        c.countDown
                    }
                }
            }
            c.await
*/
        }
    }
}
