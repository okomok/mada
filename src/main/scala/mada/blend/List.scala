

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


import list._


sealed abstract class List { // this: self =>

    private val _self = this.asInstanceOf[self]
    private[mada] type self <: List

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
    final def ::[A](e: A): addFirst[A] = Cons(e, _self)
    final type addFirst[A] = Cons[A, self]

    /**
     * Is this list nil?
     */
    type isEmpty <: meta.Boolean
    final def isEmpty(implicit _unmeta: meta.Unmeta[isEmpty, scala.Boolean]): scala.Boolean = _unmeta() // just for convenience.

    /**
     * Supports visitor iteration to return Any.
     */
    type accept_Any[v <: Visitor[Any]] <: Any

    /**
     * Supports visitor iteration to return List.
     */
    type accept_List[v <: Visitor[List]] <: List

    /**
     * Supports visitor iteration to return Nat.
     */
    type accept_metaNat[v <: Visitor[meta.Nat]] <: meta.Nat

    /**
     * Drops EXACTLY <code>n</code> elements.
     *
     * @pre `0 <= n <= size`.
     */
    final def drop[n <: meta.Nat](implicit _drop: Drop[self, n]): drop[n] = _drop(_self)
    final type drop[n <: meta.Nat] = Drop.result[self, n]

    /**
     * Takes EXACTLY <code>n</code> elements.
     *
     * @pre `0 <= n <= size`.
     */
    final def take[n <: meta.Nat](implicit _take: Take[self, n]): take[n] = _take(_self)
    final type take[n <: meta.Nat] = Take.result[self, n]

    @equivalentTo("drop[n].take[m - n]")
    final def slice[n <: meta.Nat, m <: meta.Nat](implicit _slice: Slice[self, n, m]): slice[n, m] = _slice(_self)
    final type slice[n <: meta.Nat, m <: meta.Nat] = Slice.result[self, n, m]

    /**
     * Returns the first element whose type is <code>k</code>.
     *
     * @pre List contains an element whose type is <code>k</code>.
     */
    final def elementOf[a](implicit _elementOf: ElementOf[self, a]): elementOf[a] = _elementOf(_self)
    final type elementOf[a] = ElementOf.result[self, a]

    /**
     * Returns a list without the last element.
     */
    final def init(implicit _init: Init[Nil, self]): init = _init(Nil, _self)
    final type init = Init.result[Nil, self]

    /**
     * Inserts all the elements of <code>that</code>, starting at the specified position.
     *
     * @pre `0 <= n <= size`.
     */
    final def insert[n <: meta.Nat] = new {
        def apply[that <: List](_that: that)(implicit _insert: Insert[self, n, that]): insert[n, that] = _insert(_self, _that)
    }
    final type insert[n <: meta.Nat, that <: List] = Insert.result[self, n, that]

    /**
     * Returns the last element.
     *
     * @pre <code>!isEmpty</code>.
     */
    final def last(implicit _lastOrElse: LastOrElse[self, meta.`null`]): last = _lastOrElse(_self, util.nullInstance[meta.`null`])
    final type last = LastOrElse.result[self, meta.`null`]

    /**
     * Returns the <code>n</code>-th element.
     *
     * @pre `0 <= n <= size`.
     */
    final def nth[n <: meta.Nat](implicit _nth: Nth[self, n]): nth[n] = _nth(_self)
    final type nth[n <: meta.Nat] = Nth.result[self, n]

    /**
     * Prepends <code>that</code>.
     */
    final def :::[that <: List](_that: that)(implicit _prepend: Prepend[self, that]): prepend[that] = _prepend(_self, _that)
    final type prepend[that <: List] = Prepend.result[self, that]

    /**
     * Removes <code>n</code>-th element.
     *
     * @pre `0 <= n <= size`.
     */
    final def remove[n <: meta.Nat](implicit _remove: Remove[self, n]): remove[n] = _remove(_self)
    final type remove[n <: meta.Nat] = Remove.result[self, n]

    /**
     * Replaces <code>n</code>-th element with <code>_a</code>.
     *
     * @pre `0 <= n <= size`.
     */
    final def replace[n <: meta.Nat] = new {
        def apply[a](_a: a)(implicit _replace: Replace[self, n, a]): replace[n, a] = _replace(_self, _a)
    }
    final type replace[n <: meta.Nat, a] = Replace.result[self, n, a]

    /**
     * Prepends reversed <code>that</code>.
     */
    final def reverse_:::[that <: List](_that: that)(implicit _prependReversed: PrependReversed[self, that]): prependReversed[that] = _prependReversed(_self, _that)
    final type prependReversed[that <: List] = PrependReversed.result[self, that]

    /**
     * Returns reversed one.
     */
    final def reverse(implicit _prependReversed: PrependReversed[Nil, self]): reverse = _prependReversed(Nil, _self)
    final type reverse = PrependReversed.result[Nil, self]

    /**
     * Returns the size.
     */
    final type size = Size.result[self]
    final def size(implicit _unmeta: meta.Unmeta[size, scala.Int]): scala.Int = _unmeta() // just for convenience.

    /**
     * Zips <code>that</code>.
     *
     * @pre `size <= that.size`.
     */
    final def zip[that <: List](_that: that)(implicit _zip: Zip[self, that]): zip[that] = _zip(_self, _that)
    final type zip[that <: List] = Zip.result[self, that]

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


// If you adopt `case object Nil; type Nil = Nil.type`,
// the compiler fails to search implicits.

sealed abstract class Nil extends List {
    override private[mada] type self = Nil

    override def head = throw new NoSuchElementException("head of empty list")
    override type head = meta.`null`
    override def tail = throw new NoSuchElementException("tail of empty list")
    override type tail = meta.`null` // Nil would `List.take` less-restrictive.
    override type isEmpty = meta.`true`

    override type accept_Any[v <: Visitor[Any]] = v#visitNil
    override type accept_List[v <: Visitor[List]] = v#visitNil
    override type accept_metaNat[v <: Visitor[meta.Nat]] = v#visitNil

    override def untyped = sequence.Nil
}

private[mada] object NilWrap { // works around sealed.
    val value: Nil = new Nil{}
}


final case class Cons[h, t <: List](override val head: h, override val tail: t) extends List {
    override private[mada] type self = Cons[h, t]

    override type head = h
    override type tail = t
    override type isEmpty = meta.`false`

    override type accept_Any[v <: Visitor[Any]] = v#visitCons[h, t]
    override type accept_List[v <: Visitor[List]] = v#visitCons[h, t]
    override type accept_metaNat[v <: Visitor[meta.Nat]] = v#visitCons[h, t]

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

}


/**
 * The matcher for cons list
 */
object :: {

    def unapply[h, t <: List](xs: Cons[h, t]): Option[(h, t)] = Some(xs.head, xs.tail)

}
