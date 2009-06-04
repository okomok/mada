

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Unsplit[A](_1: Iterative[Sequence[A]], _2: Iterative[A]) extends Iterative[A] {
    override def begin: Iterator[A] = {
        val ii = _1.begin // needs a fresh iterator every time.
        if (!ii) {
            iterator.theEnd
        } else {
            val h = (~ii).toIterative
            ii.++
            (h ++ bind(ii).map{ s => _2 ++ s.toIterative }.flatten).begin
        }
    }
}
