

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] final class SuccFoldRight {
     def apply[x <: Peano, z <: Any, f <: Function2](x: x, z: z, f: f): apply[x, z, f] = f.apply(x, x.decrement.foldRight(z, f))
    type apply[x <: Peano, z <: Any, f <: Function2] = f#apply[x, x#decrement#foldRight[z, f]]
}
