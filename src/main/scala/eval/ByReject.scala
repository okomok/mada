

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


case class RejectedParallelException(_1: String) extends java.util.concurrent.RejectedExecutionException(_1)


case class ByReject[R](_1: ByName[R]) extends Function0[R] {
    throw new RejectedParallelException("parallel execution rejected")
    override def apply = throw new Error("unreachable")
}

object ByReject extends Strategy {
    override def apply[R](f: => R) = new ByReject(f)
}
