

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


trait PegProxy[A] extends Peg[A] with Proxy {
    override def self: Peg[A]

    override def parse(v: Vector[A], first: Int, last: Int) = self.parse(v, first, last)
    override def length: Int = self.length

/*
    override def lookBehind = self.lookBehind
    override def not = self.not
*/
}
