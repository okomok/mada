

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


private[dual]
final class Not1[f <: Function1](f: f) extends Function1 {
    type self = Not1[f]
    override  def apply[v1 <: Any](v1: v1): apply[v1] = f.apply(v1).asInstanceOfBoolean.not
    override type apply[v1 <: Any]                    = f#apply[v1]#asInstanceOfBoolean#not
}
