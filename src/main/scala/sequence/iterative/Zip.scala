

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Zip[A, B](_1: Iterative[A], _2: Iterative[B]) extends Iterative[(A, B)] {
    override def begin = new _Iterator[Tuple2[A, B]] {
        private[this] val it1 = _1.begin
        private[this] val it2 = _2.begin

        override protected def _isEnd = !it1 || !it2
        override protected def _deref = (~it1, ~it2)
        override protected def _increment = { it1.++; it2.++ }
    }
}


@deprecated("unused")
private
case class ZipAll(_1: Vector[Sequence[Any]]) extends Iterative[Vector[Any]] {
    override def begin = new _Iterator[Vector[Any]] {
        private[this] val its: Vector[Iterator[Any]] = _1.map(_.asIterative.begin).force

        override protected def _isEnd = its.exists(_.isEnd)
        override protected def _deref = its.map(_.deref)
        override protected def _increment = its.foreach(_.increment)
    }
}
