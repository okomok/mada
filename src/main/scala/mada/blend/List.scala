

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


import list._


sealed trait List { // this: `this` =>

    private val _this = this.asInstanceOf[`this`]
    private[mada] type `this` <: List

    /**
     * Returns the first element.
     */
    def head: head
    type head

    /**
     * Returns the trailing list.
     */
    def tail: tail
    type tail <: List

    /**
     * Prepends <code>e</code>.
     */
    final def ::[A](e: A): Cons[A, `this`] = Cons(e, _this)

    /**
     * Is this list nil?
     */
    type isEmpty <: meta.Boolean
    final def isEmpty(implicit _unmeta: meta.Unmeta[isEmpty, scala.Boolean]): scala.Boolean = _unmeta() // just for convenience.

    /**
     * Supports visitor iteration.
     */
    type accept[v <: Visitor] <: v#Result

    /**
     * Drops EXACTLY <code>n</code> elements.
     *
     * @pre `0 <= n <= size`.
     */
    final def drop[n <: meta.Nat](implicit _drop: Drop[`this`, n]): drop[n] = _drop(_this)
    final type drop[n <: meta.Nat] = Drop.result[`this`, n]

    /**
     * Takes EXACTLY <code>n</code> elements.
     *
     * @pre `0 <= n <= size`.
     */
    final def take[n <: meta.Nat](implicit _take: Take[`this`, n]): take[n] = _take(_this)
    final type take[n <: meta.Nat] = Take.result[`this`, n]

    @equivalentTo("drop[n].take[m - n]")
    final def slice[n <: meta.Nat, m <: meta.Nat](implicit _slice: Slice[`this`, n, m]): slice[n, m] = _slice(_this)
    final type slice[n <: meta.Nat, m <: meta.Nat] = Slice.result[`this`, n, m]

    /**
     * Returns the first element whose type is <code>k</code>.
     *
     * @pre List contains an element whose type is <code>k</code>.
     */
    final def elementOf[a](implicit _elementOf: ElementOf[`this`, a]): elementOf[a] = _elementOf(_this)
    final type elementOf[a] = ElementOf.result[`this`, a]

    /**
     * Returns the last element.
     *
     * @pre <code>!isEmpty</code>.
     */
    final def last(implicit _lastOrElse: LastOrElse[`this`, meta.error]): last = _lastOrElse(_this, util.nullInstance[meta.error])
    final type last = LastOrElse.result[`this`, meta.error]

    /**
     * Returns the <code>n</code>-th element.
     *
     * @pre `0 <= n <= size`.
     */
    final def nth[n <: meta.Nat](implicit _nth: Nth[`this`, n]): nth[n] = _nth(_this)
    final type nth[n <: meta.Nat] = Nth.result[`this`, n]

    /**
     * Prepends <code>that</code>.
     */
    final def :::[that <: List](_that: that)(implicit _prepend: Prepend[`this`, that]): prepend[that] = _prepend(_this, _that)
    final type prepend[that <: List] = Prepend.result[`this`, that]

    /**
     * Returns a new list whose <code>n</code>-th element is replaced by <code>_a</code>.
     *
     * @pre `0 <= n <= size`.
     */
    final def replace[n <: meta.Nat, a](_a: a)(implicit _replace: Replace[`this`, n, a]): replace[n, a] = _replace(_this, _a)
    final type replace[n <: meta.Nat, a] = Replace.result[`this`, n, a]

    /**
     * Prepends reversed <code>that</code>.
     */
    final def reverse_:::[that <: List](_that: that)(implicit _reversePrepend: ReversePrepend[`this`, that]): reversePrepend[that] = _reversePrepend(_this, _that)
    final type reversePrepend[that <: List] = ReversePrepend.result[`this`, that]

    /**
     * Returns reversed one.
     */
    final def reverse(implicit _reversePrepend: ReversePrepend[Nil, `this`]): reverse = _reversePrepend(Nil, _this)
    final type reverse = ReversePrepend.result[Nil, `this`]

    /**
     * Returns the size.
     */
    final type size = Size.result[`this`]
    final def size(implicit _unmeta: meta.Unmeta[size, scala.Int]): scala.Int = _unmeta() // just for convenience.

    /**
     * Converts to <code>sequence.List[Any]</code>.
     */
    def untyped: untyped // The implicit way would annoy toString.
    final type untyped = sequence.List[Any]

    final override def equals(that: Any) = that match {
        case that: List => untyped == that.untyped
        case _ => false
    }
    final override def hashCode = untyped.hashCode
    final override def toString = untyped.toString

}


// Compiler will fail to search implicits.
// case object Nil; type Nil = Nil.type

sealed trait Nil extends List {
    private[mada] override type `this` = Nil

    override def head = throw new NoSuchElementException("head of empty list")
    override type head = meta.error
    override def tail = throw new NoSuchElementException("tail of empty list")
    override type tail = meta.error // Nil would `List.take` less-restrictive, but less-mathematical.
    override type isEmpty = meta.`true`
    override type accept[v <: Visitor] = v#visitNil

    override def untyped = sequence.Nil
}

private[mada] object NilWrap {
    val value: Nil = new Nil{}
}


final case class Cons[h, t <: List](override val head: h, override val tail: t) extends List {
    private[mada] override type `this` = Cons[h, t]

    override type head = h
    override type tail = t
    override type isEmpty = meta.`false`
    override type accept[v <: Visitor] = v#visitCons[h, t]

    override def untyped = head :: tail.untyped
}


object List {


// methodization

    sealed class _Of1[a1](_this: a1 :: Nil) {
        def toTuple: Tuple1[a1] = Tuple1(_this.head)
    }
    implicit def _of1[a1](_this: a1 :: Nil): _Of1[a1] = new _Of1(_this)

    sealed class _Of2[a1, a2](_this: a1 :: a2 :: Nil) {
        def toTuple: Tuple2[a1, a2] = Tuple2(_this.head, _this.tail.head)
    }
    implicit def _of2[a1, a2](_this: a1 :: a2 :: Nil): _Of2[a1, a2] = new _Of2(_this)

    sealed class _Of3[a1, a2, a3](_this: a1 :: a2 :: a3 :: Nil) {
        def toTuple: Tuple3[a1, a2, a3] = Tuple3(_this.head, _this.tail.head, _this.tail.tail.head)
    }
    implicit def _of3[a1, a2, a3](_this: a1 :: a2 :: a3 :: Nil): _Of3[a1, a2, a3] = new _Of3(_this)

    sealed class _Of4[a1, a2, a3, a4](_this: a1 :: a2 :: a3 :: a4 :: Nil) {
        def toTuple: Tuple4[a1, a2, a3, a4] = Tuple4(_this.head, _this.tail.head, _this.tail.tail.head, _this.tail.tail.tail.head)
    }
    implicit def _of4[a1, a2, a3, a4](_this: a1 :: a2 :: a3 :: a4 :: Nil): _Of4[a1, a2, a3, a4] = new _Of4(_this)

    sealed class _Of5[a1, a2, a3, a4, a5](_this: a1 :: a2 :: a3 :: a4 :: a5 :: Nil) {
        def toTuple: Tuple5[a1, a2, a3, a4, a5] = Tuple5(_this.head, _this.tail.head, _this.tail.tail.head, _this.tail.tail.tail.head, _this.tail.tail.tail.tail.head)
    }
    implicit def _of5[a1, a2, a3, a4, a5](_this: a1 :: a2 :: a3 :: a4 :: a5 :: Nil): _Of5[a1, a2, a3, a4, a5] = new _Of5(_this)


// For some compiler bug, these are placed in companion module.

    @compilerWorkaround
    @equivalentTo("r#prepend[l]")
    type :::[l <: List, r <: List] = r#prepend[l]

    @compilerWorkaround
    @equivalentTo("r#reversePrepend[l]")
    type reverse_:::[l <: List, r <: List] = r#reversePrepend[l]

}


/**
 * The matcher for cons list
 */
object :: {

    def unapply[h, t <: List](xs: Cons[h, t]): Option[(h, t)] = Some(xs.head, xs.tail)

}
