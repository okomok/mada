

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector.para


private[mada] object Each {
    def apply[A](v: Vector[A], f: A => Unit, grainSize: Int): Unit = {
        Assert(!IsParallel(v))
        import function.future

        if (grainSize == 1) {
            v.map{ e => future(f(e)) }.force.foreach{ u => u() }
        } else {
            v.parallelRegions(grainSize).map{ w => future(w.foreach(f)) }.
                force. // start tasks.
                    foreach{ u => u() } // join all.
        }
    }
}
