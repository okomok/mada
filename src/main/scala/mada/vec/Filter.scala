

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// force-on-access won't be thread-safe...

class FilterVector[A](v: Vector[A], p: A => Boolean) extends Adapter[A, A] with NotWritable[A] {
    override val * = {
        // This seems better than copy into ArrayList wrt worst-case space.
        val w = v.force
        w.window(0, w.stlRemoveIf(p))
    }

    override def filter(_p: A => Boolean) = *.filter({(e: A) => p(e) && _p(e)})

    /*
    private var forced = false

    override def update(i: Long, e: A) = {
        if (forced) {
            *(i) = e
        } else {
            throw new NotWritable(this)
        }
    }

    override def force = { forced = true; * }
    */
}
