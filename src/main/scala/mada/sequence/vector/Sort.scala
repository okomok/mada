

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Sort[A](_1: Vector[A], _2: compare.Func[A]) extends Forwarder[A] {
    override protected val delegate = {
        stl.Sort(_1, _1.start, _1.end, _2)
        _1
    }
}

case class StableSort[A](_1: Vector[A], _2: compare.Func[A]) extends Forwarder[A] {
    override protected val delegate = {
        stl.StableSort(_1, _1.start, _1.end, _2)
        _1
    }
}
