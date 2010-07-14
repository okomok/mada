

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


private[mada] final case class Always0[v <: Any](override val apply: v) extends Function0 {
    override  val self = this
    override type self = Always0[v]

    override type apply = v
}
