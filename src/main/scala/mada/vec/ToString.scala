

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object ToString {
    def apply[A](from: Vector[A]): String = from.toJclArrayList.toString

/* nightmare
    def apply[A](from: Vector[A]): String = {
        Vectors.stringize(wrap(
            Vectors.untokenize(from.map({ (e: A) => Vectors.fromString(e.toString) }), Vectors.fromString(", "))
        ))
    }
    private def wrap(from: Vector[Char]) = Vectors.fromString("[").append(from.drop(2)).append(Vectors.fromString("]"))
*/
}
