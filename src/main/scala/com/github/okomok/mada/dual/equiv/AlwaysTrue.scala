

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package equiv


private[mada] class AlwaysTrue extends Equiv {
    override  def self = this
    override type self = AlwaysTrue
    override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = `true`
    override type equiv[x <: Any, y <: Any] = `true`
}
