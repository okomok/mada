
package mada.rng.detail


import jcl.ToArrayList._


object ToString {
    def apply[A](r: Rng[A]): String = {
        if (r models Traversal.Forward) {
            r./.jcl_toArrayList./.toString
        } else {
            r.shallowToString
        }
    }
}
