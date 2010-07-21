

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsScanRight {
     def apply[x <: Any, xs <: List, z <: Any, f <: Function2](x: x, xs: xs, z: z, f: f): apply[x, xs, z, f] = Impl(x, xs, z, f).apply
    type apply[x <: Any, xs <: List, z <: Any, f <: Function2] = Impl[x, xs, z, f]#apply

    case class Impl[x <: Any, xs <: List, z <: Any, f <: Function2](x: x, xs: xs, z: z, f: f) extends Function0 {
        override  val self = this
        override type self = Impl[x, xs, z, f]
        val r: r = xs.scanRight(z, f)
        type r = xs#scanRight[z, f]
        override  def apply: apply = Cons(f.apply(x, r.head), r)
        override type apply = Cons[f#apply[x, r.head], r]
    }
}
