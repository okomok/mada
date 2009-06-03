

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object ToString {
    def apply[A](from: Vector[A]): String = from.toJclArrayList.toString

/* nightmare
    def apply[A](from: Vector[A]): String = {
        vector.stringize(wrap(
            vector.unsplit(from.map{ (e: A) => vector.unstringize(e.toString) })(vector.unstringize(", "))
        ))
    }
    private def wrap(from: Vector[Char]) = vector.unstringize("[") ++ from.drop(2) ++ vector.unstringize("]")
*/
}
