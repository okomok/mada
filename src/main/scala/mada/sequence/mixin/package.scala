

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object mixin {

    val force = new Mixin {
        override def apply[B](seq: Sequence[B]) = seq.force
    }

    val seal = new Mixin {
        override def apply[B](seq: Sequence[B]) = seq.seal
    }

}
