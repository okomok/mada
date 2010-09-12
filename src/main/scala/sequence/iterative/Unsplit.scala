

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private[mada] case class Unsplit[A](_1: Iterative[Sequence[A]], _2: Iterative[A]) extends Iterative[A] {
    override def begin: Iterator[A] = {
        val ii = _1.begin // needs a fresh iterator every time.
        if (!ii) {
            iterator.end
        } else {
            val h = (~ii).asIterative
            ii.++
            (h ++ bind(ii).map{ s => _2 ++ s.asIterative }.flatten).begin
        }
    }
}
