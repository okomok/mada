

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// Note: This requires copy for random-access guarantee.


object FromValues {
    // Array.apply is nightmare.
    def apply[A](es: A*): Vector[A] = Vector.fromJclArrayList(jcl.NewArrayList(es: _*)).readOnly
}
