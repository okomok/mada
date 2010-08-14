

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
final case class MakeCons[a <: Any](a: a) extends Function1 {
    type self = MakeCons[a]
    override  def apply[b <: Any](b: b): apply[b] = a :: b.asInstanceOfList
    override type apply[b <: Any]                 = a :: b#asInstanceOfList
}
