

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Equals {
    def apply[A](v: Vector[A], w: Any): Boolean = w match {
        case w: Vector[_] => v.equalsTo(w)
        case _ => false
    }
}

private[mada] object EqualsIf {
    def apply[A, B](v: Vector[A], w: Vector[B], p: (A, B) => Boolean): Boolean = {
        if (v.size != w.size) {
            false
        } else {
            stl.Equal(v, v.start, v.end, w, w.start, p)
        }
    }
}
