

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package option


private[mada] class GetOrElse {
     def apply[p <: Option, f <: Function0](p: p, f: f): apply[p, f] = `if`(p.isEmpty, f, Get(p)).apply
    type apply[p <: Option, f <: Function0] = `if`[p#isEmpty, f, Get[p]]#apply

    final case class Get[p <: Option](p: p) extends Function0 {
        override  def self = this
        override type self = Get[p]
        override  def apply: apply = p.get
        override type apply = p#get
    }
}
