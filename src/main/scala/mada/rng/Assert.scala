
package mada.rng


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
