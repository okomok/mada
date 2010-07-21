

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


private[mada] final class Const0[v <: Any](v: => v) extends Function0 {
    override  val self = this
    override type self = Const0[v]

    override lazy val apply: apply = v
    override type apply = v
}
