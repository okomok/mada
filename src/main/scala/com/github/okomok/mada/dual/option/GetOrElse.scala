

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package option


private[mada] final case class GetOrElse[p <: Option, f <: Function0](p: p, f: f) {
     def apply = p.isEmpty.`if`(f, Get()).apply
    type apply = p#isEmpty#`if`[f, Get]#apply

    final case class Get() extends Function0 {
        override  def self = this
        override type self = Get
        override  def apply: apply = p.get
        override type apply = p#get
    }
}
