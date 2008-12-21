

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Exists {
    def apply[A](v : Vector[A], p: A => Boolean): Boolean = v.find(p) != None
}
