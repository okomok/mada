

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


class FilterVector[A](v: Vector[A], p: A => Boolean) extends Vector[A] {
    // force-on-access won't be thread-safe...
    private val * = {
        val w = v.force
        w.window(0, w.stlRemoveIf(p))
    }

    override def size = *.size
    override def apply(i: Long) = *(i)
    override def update(i: Long, e: A) = *(i) = e

    override def filter(_p: A => Boolean) = v.filter({(e: A) => p(e) && _p(e)})

    /*
    override def loop[F <: (A => Boolean)](f: F) = {
        v.loop({ (e: A) => if (p(e)) f(e) else true })
        f
    }
    */
}
