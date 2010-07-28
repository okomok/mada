

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package option


private[mada] final class Map {
     def apply[p <: Option, f <: Function1](p: p, f: f): apply[p, f] = Some(f.apply(p.get))
    type apply[p <: Option, f <: Function1] = Some[f#apply[p#get]]
}
