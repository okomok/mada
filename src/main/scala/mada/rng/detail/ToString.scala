
package mada.rng.detail


import jcl.ToArrayList._


object ToString {
    def apply[A](r: Rng[A]): String = {
        new StringBuilder().
            append(r.toExpr.jcl_toArrayList.eval).
            append(" as [").append(r.begin).append(", ").append(r.end).append(")").
        toString
    }
}
