

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class FolderLeft[A, B](_1: Vector[A], _2: B, _3: (B, A) => B) extends Forwarder[B] {
    override protected val delegate = {
        val a = new Array[B](_1.size + 1)
        var i = 0; var acc = _2
        a(i) = acc
        for (e <- _1) {
            i += 1; acc = _3(acc, e)
            a(i) = acc
        }
        vector.fromArray(a).
            readOnly // consistent with parallels.
    }
}
