
package mada.rng


import Equals._
import Filter._
import Flatten._
import Foreach._
import From._
import Map._
import ReadOnly._
import ShallowEquals._
import jcl.ToArrayList._


object Rng extends Namespace
        with ToArray
        with AsRngBy
        with AsRngOf
        with Begin with End
        with ToCell
        with Compatibles
        with CopyTo
        with Indirect with Outdirect
        with Distance
        with Drop
        with DropWhile
        with Equals
        with EqualsTo
        with Exists
        with Filter
        with Find
        with FindPointerOf
        with First with Last
        with FoldLeft
        with Forall
        with Force
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
        with ShallowEquals
        with Size
        with Slice
        with Take
        with TakeWhile
        with ToRng


trait Rng[A] {
    protected def _begin: Pointer[A]
    protected def _end: Pointer[A]

    final def begin = _begin
    final def end = _end
    final lazy val traversal = begin.traversal
    final def models(t: Traversal): Boolean = traversal <:< t

    override def equals(that: Any) = equals(that.asInstanceOf[Rng[A]])
    override def toString = detail.ToString(this)

    final def equals(that: Rng[A]) = toExpr.requals(that).eval
    final def shallowEquals(that: Rng[A]) = toExpr.shallowEquals(that).eval
    final def shallowToString = detail.ShallowToString(this)
    final def readOnly = toExpr.readOnly.eval

    final def rng = this
    final def toExpr = ExprV2.Constant(this)
    final def toPair = (begin, end)

// for-comprehension
    final def map[B](f: A => B) = toExpr.map(f).eval
    final def flatMap[B](f: A => Rng[B]) = toExpr.map(f).flatten.eval
    final def filter(p: A => Boolean) = toExpr.filter(p).eval
    final def foreach(f: A => Unit) = toExpr.foreach(f).eval
}
