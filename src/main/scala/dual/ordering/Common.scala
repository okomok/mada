

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


private[dual]
trait Common {
    val LT: LT = _Result.LT
    val GT: GT = _Result.GT
    val EQ: EQ = _Result.EQ

    /**
     * Always returns `EQ`.
     */
     val alwaysEQ = new AlwaysEQ
    type alwaysEQ = AlwaysEQ
}
