
package mada.rng.detail


import jcl.ToArrayList._


object ToString {
    def apply[A](r: Rng[A]): String = r.toExpr.jcl_toArrayList.eval.toString
}
