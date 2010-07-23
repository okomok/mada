

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


private[mada] final class Union {
    // left-biased
    @symmetricTypeMismatchWorkaround
     def apply[m <: Any, w <: Any](m: m, w: w): apply[m, w] = w.asInstanceOfMap.putList(m.asInstanceOfMap.toList)//.asInstanceOf[apply[m, w]]
    type apply[m <: Any, w <: Any] = w#asInstanceOfMap#putList[m#asInstanceOfMap#toList]
}
