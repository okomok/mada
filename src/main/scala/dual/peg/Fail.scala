

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
final class Fail extends AbstractPeg with ZeroWidth {
    type self = Fail

    override  def parse[xs <: List](xs: xs): parse[xs] = Failure(xs)
    override type parse[xs <: List]                    = Failure[xs]
}
