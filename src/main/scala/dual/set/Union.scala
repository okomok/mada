

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[mada] final class Union {
    // left-biased
    @symmetricTypeMismatchWorkaround
     def apply[s <: Any, z <: Any](s: s, z: z): apply[s, z] = z.asInstanceOfSet.addList(s.asInstanceOfSet.toList)//.asInstanceOf[apply[s, z]]
    type apply[s <: Any, z <: Any] = z#asInstanceOfSet#addList[s#asInstanceOfSet#toList]
}
