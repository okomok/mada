

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


private[mada] final class Union {
    // left-biased
    @nothingTypeMismatchWorkaround
     def apply[m <: Any, w <: Any](m: m, w: w): apply[m, w] = w.asInstanceOfMap.putSeq(m.asInstanceOfMap.toList)//.asInstanceOf[apply[m, w]]
    type apply[m <: Any, w <: Any] = w#asInstanceOfMap#putSeq[m#asInstanceOfMap#toList]
}
