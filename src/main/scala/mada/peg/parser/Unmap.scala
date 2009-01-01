

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Unmap {
    def apply[Z, A](p: Parser[A], f: Z => A): Parser[Z] = p.prescan((_: Vector[Z]).map(f))
}
