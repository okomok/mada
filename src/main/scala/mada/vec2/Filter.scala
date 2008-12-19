

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


class FilterVector[A](v: Vector[A], p: A => Boolean) extends Adapter[A, A] with NotWritable[A] {
    // force-on-access won't be thread-safe...
    override val * = {
        // This seems better than copy into ArrayList wrt worst-case space.
        val w = v.force
        w.window(0, w.stlRemoveIf(p))
    }

    override def filter(_p: A => Boolean) = *.filter({(e: A) => p(e) && _p(e)})

    /*
    override def loop[F <: (A => Boolean)](f: F) = {
        ?.loop({ (e: A) => if (p(e)) f(e) else true })
        f
    }
    */
}
