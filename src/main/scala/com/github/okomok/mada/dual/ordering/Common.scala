

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


private[mada] trait Common {
    val LT: LT = _Result.LT
    val GT: GT = _Result.GT
    val EQ: EQ = _Result.EQ

     val ofNatPeano = new OfNatPeano
    type ofNatPeano = OfNatPeano

     val ofNatDense = new OfNatDense
    type ofNatDense = OfNatDense
}
