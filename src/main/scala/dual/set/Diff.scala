

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[mada] final class Diff {
     def apply[s <: Any, z <: Set](s: s, z: z): apply[s, z] = s.asInstanceOfSet.removeList(z.toList)//.asInstanceOf[apply[s, z]]
    type apply[s <: Any, z <: Set] = s#asInstanceOfSet#removeList[z#toList]
}
