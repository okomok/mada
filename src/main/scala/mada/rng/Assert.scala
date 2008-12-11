
package mada.rng


import From._
import IsEmpty._


object AssertModels {
    def apply[A](p: Pointer[A], t: Traversal) = {
        Assert(msg(t, p.traversal), p models t)
    }

    def apply[A](r: Rng[A], t: Traversal) = {
        Assert(msg(t, r.traversal), r models t)
    }

    private def msg[A](expected: Traversal, actual: Traversal) = {
        new StringBuilder().
            append("requires:<").append(expected.toString).append('>').
            append(" but was:<").append(actual.toString).append('>').
            toString
    }
}


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
