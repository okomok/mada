

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class Eqv[ee <: Equiv](ee: ee) extends Equiv {
    type self = Eqv[ee]
    override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = x.asInstanceOfSeq.equivTo(y.asInstanceOfSeq, ee)
    override type equiv[x <: Any, y <: Any] = x#asInstanceOfSeq#equivTo[y#asInstanceOfSeq, ee]
}
