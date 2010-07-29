

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsAppend {
     def apply[x <: Any, xs <: List, ys <: Seq](x: x, xs: xs, ys: ys): apply[x, xs, ys] = Cons(x, xs.append(ys))
    type apply[x <: Any, xs <: List, ys <: Seq] = Cons[x, xs#append[ys]]
}

private[mada] final class ConsReverseAppend {
     def apply[x <: Any, xs <: List, ys <: List](x: x, xs: xs, ys: ys): apply[x, xs, ys] = xs.reverseAppend(Cons(x, ys))
    type apply[x <: Any, xs <: List, ys <: List] = xs#reverseAppend[Cons[x, ys]]
}
