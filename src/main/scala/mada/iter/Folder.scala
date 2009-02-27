

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object FolderLeft {
    def apply[A, B](it: Iterator[A], z: B, op: (B, A) => B): Iterator[B] = new FolderLeftIterator(it, z, op)
}

private[mada] class FolderLeftIterator[A, B](it: Iterator[A], private var z: B, op: (B, A) => B) extends Iterator[B] {
    private var isSeed = true
    override def hasNext = isSeed || it.hasNext
    override def next = {
        if (isSeed) {
            isSeed = false
        } else {
            z = op(z, it.next)
        }
        z
    }
}
