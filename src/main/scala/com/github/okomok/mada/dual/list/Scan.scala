

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsScanRightCons {
     def apply[x <: Any, ys <: List, f <: Function2](x: x, ys: ys, f: f): apply[x, ys, f] = Cons(f.apply(x, ys.head), ys)
    type apply[x <: Any, ys <: List, f <: Function2] = Cons[f#apply[x, ys#head], ys]
}
