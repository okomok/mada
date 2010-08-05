

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada]
final class Iterate[z <: Any, f <: Function1](z: z, f: f) {
     def apply = unfoldRight(z, new Iter)
    type apply = unfoldRight[z,     Iter]

    class Iter extends Function1 {
        type self = Iter
        override  def apply[x <: Any](x: x): apply[x] = Some(Tuple2(x, f.apply(x)))
        override type apply[x <: Any]                 = Some[Tuple2[x, f#apply[x]]]
    }
}
