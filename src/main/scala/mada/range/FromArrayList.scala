
package mada.range


import  scala.collection.jcl.ArrayList


object FromArrayList {
    def apply[A](a: ArrayList[A]): ArrayListRange[A] = new ArrayListRange(a)

    def apply[A](es: A*): ArrayListRange[A] = {
        val a = new ArrayList[A]
        var i = 0
        for (e <- es.elements) {
            a.add(i, e)
            i = i + 1
        }
        apply(a)
    }
}

class ArrayListRange[A](val arrayList: ArrayList[A])
        extends PointerRange[A](new ArrayListPointer(arrayList, 0), new ArrayListPointer(arrayList, arrayList.length)) {
}

class ArrayListPointer[A](private val a: ArrayList[A], private var i: Int)
        extends PointerAdapter[Long, A, ArrayListPointer[A]] {
    override val _base = new NumberPointer(i)
    override def _read = a(derefBase)
    override def _write(e: A) = a.set(derefBase, e)
    override def _clone = new ArrayListPointer(a, derefBase)
    private def derefBase: Int = *(base).toInt
}
