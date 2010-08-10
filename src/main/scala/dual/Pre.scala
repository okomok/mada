

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * unneeded if `assert[c]` could be implemented.
 */
@specializer
sealed abstract class Pre[c <: Boolean]

object Pre {
    implicit val _ofTrue = new Pre[`true`]{}
}
