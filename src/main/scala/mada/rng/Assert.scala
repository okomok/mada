
package mada.rng


import From._
import IsEmpty._


object AssertModels {
    def apply[A](r: Rng[A], t: Traversal) {
        Assert(msg(r, t), r models t)
    }

    private def msg[A](r: Rng[A], t: Traversal) = {
        new StringBuilder().
            append("requires:<").append(t.toString).append('>').
            append(" but was:<").append(r.traversal.toString).append('>').
            toString
    }
}


object AssertNotEmpty {
    def apply[A](r: Rng[A]) {
        Assert(msg(r), !from(r).rng_isEmpty.eval)
    }

    private def msg[A](r: Rng[A]) = {
        new StringBuilder().
            append("requires:<").append("empty rng").append('>').
            append(" but was:<").append(r.toString).append('>').
            toString
    }
}

