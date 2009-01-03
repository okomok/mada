

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Eps {
    def apply[A]: Parser[A] = new EpsParser[A]
}

class EpsParser[A] extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = first
    override def length = 0
}
