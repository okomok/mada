

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class ConsFalse {
     def apply[xs <: Dense](xs: xs): apply[xs] = `if`(xs.isEmpty, always0(xs), always0(Cons(`false`, xs))).apply
    type apply[xs <: Dense] = `if`[xs#isEmpty, always0[xs], always0[Cons[`false`, xs]]]#apply
}
