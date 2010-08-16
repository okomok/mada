

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


private[dual]
final class AlwaysEQ extends AbstractOrdering {
    type self = AlwaysEQ
    override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = `true`
    override type equiv[x <: Any, y <: Any]                          = `true`
    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] = EQ
    override type compare[x <: Any, y <: Any]                            = EQ
}
