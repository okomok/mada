

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Map[A, B](_1: Traversable[A], _2: A => B) extends Traversable[B] {
    override def begin = new Traverser[B] {
        private val t = _1.begin

        override def isEnd = !t
        override def deref = _2(~t)
        override def increment = t.++
    }

    override def map[C](f: B => C) = _1.map(f compose _2) // map-map fusion
}
