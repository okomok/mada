
package mada.range


object Copy {
    final def apply[E, R <% Range[E], O <% Output[E]](r: R, o: O): O = {
        val first = r.begin
        var last = r.end
        while (first !== last) {
            o << first.read
        }
        o
    }
}
