

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


package object peano extends peano.LiteralCommon with peano.OperatorCommon {

    @equivalentTo("new Zero{}")
    val Zero = _Peano.Zero

    private[mada] val Singular = _Peano.Singular

}
