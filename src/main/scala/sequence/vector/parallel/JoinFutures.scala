

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
object JoinFutures {
    def apply(fs: Vector[eval.Future[Unit]]) = fs.foreach(f => f())
}
