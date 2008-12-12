

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.detail


object ShallowToString {
    def apply[A](r: Rng[A]): String = {
        new StringBuilder().
            append("[").append(r.begin).append(", ").append(r.end).append(")").
        toString
    }
}
