

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] final class SuccIncrement {
     def apply[x <: Peano](x: x): apply[x] = Succ(x)
    type apply[x <: Peano] = Succ[x]
}
