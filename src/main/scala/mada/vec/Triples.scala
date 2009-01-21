

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object TriplesVector {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[Vector[A]] = new TriplesVector(vv)
}

class TriplesVector[A](val triples: Vector[Vector.Triple[A]]) extends VectorProxy[Vector[A]] with NotWritable[Vector[A]] {
    override val self = triples.map({ v => Vector.tripleVector(v) })
}


object VectorTriples {
    def apply[A](vv: Vector[Vector[A]]): Vector[Vector.Triple[A]] = vv match {
        case vv: TriplesVector[_] => vv.triples // conversion fusion
        case _ => vv.map({ v => v.triple })
    }
}
