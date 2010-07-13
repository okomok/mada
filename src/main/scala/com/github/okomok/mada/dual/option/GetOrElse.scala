

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package option


private[mada] final class GetOrElse {
     def apply[p <: Option, f <: Function0](p: p, f: f): apply[p, f] = `if`(p.isEmpty, f, new Get(p, f)).apply
    type apply[p <: Option, f <: Function0] = `if`[p#isEmpty, f, Get[p, f]]#apply

    class Get[p <: Option, f <: Function0](p: p, f: f) extends Function0 {
        override  val self = this
        override type self = Get[p, f]
        override  def apply: apply = p.get
        override type apply = p#get
    }
}
