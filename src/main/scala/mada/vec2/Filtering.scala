

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


// neither readable nor writable.
// filtering(p).map(f) // error
// filtering(p).foreach(f) // ok
// filtering(p).force.map(f) // ok

class FilteringVector[A](v: Vector[A], p: A => Boolean) extends Vector[A] {
    override def size = v.size

    override def filter(_p: A => Boolean) = v.filter({(e: A) => p(e) && _p(e)})

    override def loop[F <: (A => Boolean)](f: F) = {
        v.loop({ (e: A) => if (p(e)) f(e) else true })
        f
    }
}
