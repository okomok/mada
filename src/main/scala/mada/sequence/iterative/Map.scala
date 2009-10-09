

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


case class Map[A, +B](_1: Iterative[A], _2: A => B) extends Iterative[B] {
    override def begin = new Iterator[B] {
        private val it = _1.begin

        override def isEnd = !it
        override def deref = _2(~it)
        override def increment = it.++
    }

    override def map[C](f: B => C) = _1.map(f compose _2) // map-map fusion
}
