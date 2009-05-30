

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


package object mixin {

    val force = new Mixin {
        override def apply[B](seq: Iterative[B]) = seq.force
    }

    val seal = new Mixin {
        override def apply[B](seq: Iterative[B]) = seq.seal
    }

}
