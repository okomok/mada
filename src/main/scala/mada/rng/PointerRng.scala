
package mada.rng


class PointerRng[A](override val _begin: Pointer[A], override val _end: Pointer[A]) extends Rng[A] {
    def this(r: Rng[A]) = this(r.begin, r.end)
    def copy: PointerRng[A] = new PointerRng(begin.copy, end.copy)
    final def equalsSubRng(that: PointerRng[A]): Boolean = (begin == that.begin) && (end == that.end)
}
