

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object TriplesVector {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[Vector[A]] = vv match {
        case vv: VectorTriples[_] => vv.from // conversion fusion
        case _ => new TriplesVector(vv)
    }
}

class TriplesVector[A](val from: Vector[Vector.Triple[A]]) extends VectorProxy[Vector[A]] with NotWritable[Vector[A]] {
    override val self = from.map({ v => Vector.tripleVector(v) })
}


object VectorTriples {
    def apply[A](vv: Vector[Vector[A]]): Vector[Vector.Triple[A]] = vv match {
        case vv: TriplesVector[_] => vv.from // conversion fusion
        case _ => new VectorTriples(vv)
    }
}

class VectorTriples[A](val from: Vector[Vector[A]]) extends VectorProxy[Vector.Triple[A]] with NotWritable[Vector.Triple[A]] {
    override val self = from.map({ v => v.triple })
}
