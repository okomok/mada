

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import From._
import IsEmpty._


object AssertNotEmpty {
    def apply[A](r: Rng[A]) = {
        Assert(msg(r), !from(r).isEmpty.eval)
    }

    private def msg[A](r: Rng[A]) = {
        new StringBuilder().
            append("requires:<").append("empty rng").append('>').
            append(" but was:<").append(r.toString).append('>').
            toString
    }
}
