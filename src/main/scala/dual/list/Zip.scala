

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsZip {
     def apply[x <: Any, xs <: List, ys <: Seq](x: x, xs: xs, ys: ys): apply[x, xs, ys] = Cons(Tuple2(x, ys.head), xs.zip(ys.tail))
    type apply[x <: Any, xs <: List, ys <: Seq] = Cons[Tuple2[x, ys#head], xs#zip[ys#tail]]
}
