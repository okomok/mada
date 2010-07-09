

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


final class ByLazy[x <: Any](x: => x) extends Function0 {
    override  val self = this
    override type self = ByLazy[x]
    override lazy val apply: apply = x
    override type apply = x
}
