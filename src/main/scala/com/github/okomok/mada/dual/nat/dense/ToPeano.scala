

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class ConsToPeano {
     def apply[xs <: Dense](xs: xs): apply[xs] = peano.Succ(xs.decrement.toPeano)
    type apply[xs <: Dense] = peano.Succ[xs#decrement#toPeano]
}
