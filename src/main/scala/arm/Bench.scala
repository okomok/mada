

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


import scala.compat.Platform


@visibleForTesting
class Bench extends Arm[Unit] {
    private var s: Long = _
    override def open = s = Platform.currentTime
    override def close() = println((Platform.currentTime - s) + "ms")
}
