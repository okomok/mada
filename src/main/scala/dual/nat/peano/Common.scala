

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[dual]
class Common extends CommonLiteral {
    @returnThis
    val Literal: CommonLiteral = this

    @equivalentTo("new Zero{}")
    val Zero = _Peano.Zero
}
