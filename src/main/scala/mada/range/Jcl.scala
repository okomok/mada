
package mada.range


import scala.collection.jcl.ArrayList


object FromArrayList {
    def apply[A](a: ArrayList[A]): Range[A] = {
        val ia = new IndexAccess[A] {
            override def _set(i: Long, e: A) = a(i.toInt)
            override def _get(i: Long) = a(i.toInt)
            override def _length = a.length
        }
        FromIndexAccess(ia)
    }

    def apply[A](es: A*): Range[A] = {
        val a = new ArrayList[A]
        for (e <- es.elements) {
            a.add(e)
        }
        apply(a)
    }
}

object ToArrayList {
    def apply[A](r: Range[A]): ArrayList[A] = {
        val a = new ArrayList[A]
        r.forEach({(e: A) => a.add(e)})
        a
    }
}
