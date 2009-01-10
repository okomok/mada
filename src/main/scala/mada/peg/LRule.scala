

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: "Packrat Parsers Can Support Left Recursion"


package mada.peg


class LRule[A](v: Vector[A]) extends Peg[A] {
    private var p: Peg[A] = null
    private val mp = new Peg.Memoizer(v).memoize(this)

    def left: Peg[A] = mp
    def ::=(that: Peg[A]): Unit = { p = that }
    def <--(that: Peg[A]): Unit = { this ::= that }

    private var parsing = false
    private var recurred = false

    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (parsing) {
            recurred = true
            FAILURE
        } else {
            parsing = true
            val cur = p.parse(v, first, last)
            mp.table.put(first, cur)
            parsing = false
            if (recurred) {
                println("recurred:" + cur)
                recurred = false
                if (cur != FAILURE) {
                    grow(v, first, last)
                } else {
                    cur
                }
            } else {
                cur
            }
        }
    }

    private def grow(v: Vector[A], first: Long, last: Long): Long = {
        var cur = first
        while (true) {
            val i = p.parse(v, first, last)
            println("iteration:" + i)
            if (i == FAILURE || i <= cur) {
                return cur
            }
            cur = i
            mp.table.put(first, cur)
        }
        cur
    }
}
