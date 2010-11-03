

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


// See: http://jim-mcbeath.blogspot.com/2010/09/standalone-generic-scala-generator.html


import scala.util.continuations
import continuations.suspendable


private
case class Block[+A](_1: (A => Unit @suspendable) => Unit @suspendable) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private[this] var _e: Option[A] = None
        private[this] var _k: Option[Unit => Unit] = None
        private[this] val _y = new (A => Unit @suspendable) {
            override def apply(e: A) = {
                _e = Some(e)
                _suspend
            }
        }

        continuations.reset {
            _suspend
            _1(_y)
        }
        _k.get.apply()

        override def isEnd = _e.isEmpty
        override def deref = {
            preDeref
            _e.get
        }
        override def increment = {
            preIncrement
            _e = None
            _k.get.apply()
        }

        private def _suspend: Unit @suspendable = continuations.shift { (k: Unit => Unit) => _k = Some(k) }
    }
}
