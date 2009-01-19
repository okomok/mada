

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ToString {
    def apply[A](v: Vector[A]): String = v.toJclArrayList.toString

/* nightmare
    def apply[A](v: Vector[A]): String = {
        Vector.stringize(wrap(
            Vector.untokenize(v.map({ (e: A) => Vector.stringVector(e.toString) }), Vector.stringVector(", "))
        ))
    }
    private def wrap(v: Vector[Char]) = Vector.stringVector("[").append(v.drop(2)).append(Vector.stringVector("]"))
*/
}
