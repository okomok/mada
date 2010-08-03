

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Count[it <: Iterator, f <: Function1](it: it, f: f) extends Function0 {
    type self = Count[it, f]
    override  def apply: apply = new Length(new Filter(it, f)).apply
    override type apply        =     Length[    Filter[it, f]]#apply
}
