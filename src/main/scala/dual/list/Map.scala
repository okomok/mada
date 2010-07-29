

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsMap {
     def apply[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f): apply[x, xs, f] = Cons(f.apply(x), xs.map(f))
    type apply[x <: Any, xs <: List, f <: Function1] = Cons[f#apply[x], xs#map[f]]
}
