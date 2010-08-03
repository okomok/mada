

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Count[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
    type self = Count[xs, f]
    override  def apply: apply = new Length(new Filter(xs, f)).apply
    override type apply        =     Length[    Filter[xs, f]]#apply
}
