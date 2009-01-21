

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


trait PegProxy[A] extends Peg[A] with Proxy {
    override def self: Peg[A]

    override def parse(v: Vector[A], start: Int, end: Int) = self.parse(v, start, end)
    override def length: Int = self.length

/*
    override def lookBehind = self.lookBehind
    override def not = self.not
*/
}
