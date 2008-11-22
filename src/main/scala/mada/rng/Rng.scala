
package mada.rng


import Equals._
import From._
import ToArrayList._


object Rng extends Traits
        with ToArray
        with AsRngBy
        with AsRngOf
        with Begin with End
        with ToCell
        with Compatibles
        with Copy with CopyTo
        with Indirect with Outdirect
        with Distance
        with Drop with DropWhile
        with Equals with EqualsTo
        with Exists
        with Filter
        with Find with FindPointerOf
        with First with Last
        with FoldLeft
        with Forall
        with Foreach
        with From
        with IsEmpty
        with Loop
        with Map
        with Mismatch
        with Offset
        with Permutation
        with ReadOnly
        with Reverse
        with Size
        with Slice
        with Take with TakeWhile
        with ToRng


trait Rng[A] {
    protected def _begin: Pointer[A]
    protected def _end: Pointer[A]

    final def begin = _begin
    final def end = _end
    final lazy val traversal = begin.traversal
    final def models(t: Traversal): Boolean = traversal <:< t

    override def equals(that: Any) = equals(that.asInstanceOf[Rng[A]])
    def equals(that: Rng[A]) = from(this).rng_equals(that).eval
    override def toString = from(this).rng_toArrayList.eval.toString // TODO

    final def rng = this
}
