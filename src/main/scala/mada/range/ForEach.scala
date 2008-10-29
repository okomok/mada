
package mada.range


case class ForEach[E] extends ((Range[E], E => Any) => (E => Any)) {
    final def apply(r: Range[E], f: E => Any) = {
        val first = r.begin
        val last = r.end
        while (first != last) {
            f(first.read)
            first++
        }
        f
    }
}


object ForEach {
    final def apply[E](r: Range[E], f: E => Any): E => Any = ForEach[E]()(r, f)
}
