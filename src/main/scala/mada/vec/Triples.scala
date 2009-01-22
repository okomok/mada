

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object TriplesVector {
    def apply[A](from: Vector[Vector.Triple[A]]): Vector[Vector[A]] = from match {
        case from: VectorTriples[_] => from.from // conversion fusion
        case _ => new TriplesVector(from)
    }
}

class TriplesVector[A](val from: Vector[Vector.Triple[A]]) extends VectorProxy[Vector[A]] with NotWritable[Vector[A]] {
    override val self = from.map({ v => Vector.tripleVector(v) })
}


object VectorTriples {
    def apply[A](from: Vector[Vector[A]]): Vector[Vector.Triple[A]] = from match {
        case from: TriplesVector[_] => from.from // conversion fusion
        case _ => new VectorTriples(from)
    }
}

class VectorTriples[A](val from: Vector[Vector[A]]) extends VectorProxy[Vector.Triple[A]] with NotWritable[Vector.Triple[A]] {
    override val self = from.map({ v => v.triple })
}
