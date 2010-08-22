

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


private[dual]
final class Const0[v <: Any](v: => v) extends Function0 {
    type self = Const0[v]
    override lazy val apply: apply = v
    override     type apply        = v
}
