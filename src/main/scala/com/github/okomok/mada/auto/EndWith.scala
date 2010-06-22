

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[mada] case class EndWith(_1: util.ByName[Unit]) extends Auto[Unit] {
    override def get = ()
    override def end = _1()
}
