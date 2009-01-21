

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object TripleVector {
    def apply[A](v: Vector.Triple[A]): Vector[A] = v._1.window(v._2, v._3)
}

object VectorTriple {
    def apply[A](v: Vector[A]): Vector.Triple[A] = (v, 0, v.size)
}


object TriplesVector {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[Vector[A]] = new TriplesVector(vv)
}

class TriplesVector[A](val triples: Vector[Vector.Triple[A]]) extends VectorProxy[Vector[A]] with NotWritable[Vector[A]] {
    override val self = triples.map({ v => Vector.tripleVector(v) })
}

object VectorTriples {
    def apply[A](vv: Vector[Vector[A]]): Vector[Vector.Triple[A]] = vv match {
        case vv: TriplesVector[_] => vv.triples // from-to fusion
        case _ => vv.map({ v => v.triple })
    }
}

