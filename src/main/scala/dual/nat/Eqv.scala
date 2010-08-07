

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


private[dual]
final class Eqv extends Equiv {
    type self = Eqv
    override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = x.asInstanceOfNat.equal(y.asInstanceOfNat)
    override type equiv[x <: Any, y <: Any]                          = x#asInstanceOfNat#equal[y#asInstanceOfNat]
}
