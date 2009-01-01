

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


trait ParserProxy[A] extends Parser[A] with Proxy {
    override def self: Parser[A]

    override def parse(s: Scanner[A], first: Long, last: Long): Long = self.parse(s, first, last)
    override def length: Long = self.length
}
