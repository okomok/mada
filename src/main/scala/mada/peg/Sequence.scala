

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class FromSequence[A](_1: sequence.Iterative[A]) extends Forwarder[A] {
    override protected val delegate = fromSequenceBy(_1)(function.equal)
}

case class FromSequenceBy[A](_1: sequence.Iterative[A], _2: (A, A) => Boolean) extends Forwarder[A] {
    override protected val delegate: Peg[A] = _1 match {
        case _1: sequence.Vector[_] => new _FromVectorBy(_1.asInstanceOf[sequence.Vector[A]], _2)
        case _ => new _FromSequenceBy(_1, _2)
    }
}


private class _FromSequenceBy[A](_1: sequence.Iterative[A], _2: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int): Int = {
        val it = _1.begin
        var cur = start

        while (!it.isEnd && cur != end) {
            if (!_2(~it, v(cur))) {
                return FAILURE
            }
            it.++
            cur += 1
        }

        if (cur == end && !it.isEnd) FAILURE else cur
    }

    override def width = _1.size
}


private class _FromVectorBy[A](_1: sequence.Vector[A], _2: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val wsize = _1.size
        if (end - start < wsize) {
            FAILURE
        } else if (sequence.vector.stl.Equal(_1, _1.start, _1.end, v, start, _2)) {
            start + wsize
        } else {
            FAILURE
        }
    }

    override def width = _1.size
}
