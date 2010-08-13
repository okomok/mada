

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
final class Eps extends AbstractPeg with ZeroWidth {
    type self = Eps

    override  def parse[xs <: List](xs: xs): parse[xs] = Success(Nil, xs)
    override type parse[xs <: List]                    = Success[Nil, xs]
}
