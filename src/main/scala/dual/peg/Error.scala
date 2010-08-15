

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
final class Error extends AbstractPeg with ZeroWidth {
    type self = Error

    override  def parse[xs <: List](xs: xs): parse[xs] = throw new ParseError(xs.undual.toString)
    override type parse[xs <: List]                    = Nothing
}

final case class ParseError(input: String) extends java.lang.Error
