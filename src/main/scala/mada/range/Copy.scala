
package mada.range


object Copy {
    final def apply[E](r: Range[E], o: Output[E]): Output[E] = {
        val first = r.begin; val last = r.end
        while (first != last) {
            o << first.read
            first.pre_++
        }
        o
    }

    def test(r: Range[Int], o: Output[Int]) = {
        Copy(r, o)
    }

    def test(r: Range[Int], f: Int => Boolean) = {
        Copy(r, FunctionOutput(f))
    }
}
