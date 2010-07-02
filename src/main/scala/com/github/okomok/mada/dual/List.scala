

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


import list._


sealed abstract class List { // this: self =>

    @returnThis
     def self: self
    type self <: List

    /**
     * Returns the first element.
     */
     def head: head
    type head <: Any // Not metageneric

    /**
     * Returns the trailing list.
     */
     def tail: tail
    type tail <: List

    @equivalentTo("Cons(e, self)")
    final  def addFirst[e <: Any](e: e): addFirst[e] = Cons(e, self)
    final type addFirst[e <: Any] = Cons[e, self]

    /**
     * Is this list nil?
     */
     def isEmpty: isEmpty
    type isEmpty <: Boolean

    /**
     * Drops EXACTLY <code>n</code> elements.
     *
     * @pre `n in [0, size)`.
     */
    final  def drop[n <: Nat](n: n): drop[n] = new Drop().apply(self, n)
    final type drop[n <: Nat] = Drop#apply[self, n]

    /**
     * Takes EXACTLY <code>n</code> elements.
     *
     * @pre `n in [0, size)`.
     */
    final  def take[n <: Nat](n: n): take[n] = reverse.drop(size - n).reverse //Take.apply(self, n)
    final type take[n <: Nat] = reverse#drop[size# -[n]]#reverse //Take.apply[self, n]

    @equivalentTo("take(m).drop(n)")
    final  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = take(m).drop(n)
    final type slice[n <: Nat, m <: Nat] = take[m]#drop[n]

    /**
     * Returns a list without the last element.
     */
    final  def init: init = reverse.tail.reverse
    final type init = reverse#tail#reverse

    /**
     * Inserts all the elements of <code>that</code>, starting at the specified position.
     *
     * @pre `n in [0, size)`.
     */
    final  def insert[n <: Nat, that <: List](n: n, that: that): insert[n, that] = take(n) ::: that ::: drop(n)
    final type insert[n <: Nat, that <: List] = take[n] ::: that ::: drop[n]

    /**
     * Returns the last element.
     *
     * @pre <code>!isEmpty</code>.
     */
    final  def last: last = nth(size.decrement)
    final type last = nth[size#decrement]

    /**
     * Returns the <code>n</code>-th element.
     *
     * @pre `n in [0, size)`.
     */
    final  def nth[n <: Nat](n: n): nth[n] = drop(n).head
    final type nth[n <: Nat] = drop[n]#head

    /**
     * Prepends <code>that</code>.
     */
    final  def prepend[that <: List](that: that): prepend[that] = new Prepend().apply(self, that)
    final type prepend[that <: List] = Prepend#apply[self, that]

    /**
     * Removes <code>n</code>-th element.
     *
     * @pre `n in [0, size)`.
     */
    final  def remove[n <: Nat](n: n): remove[n] = take(n) ::: drop(n.increment)
    final type remove[n <: Nat] = take[n] ::: drop[n#increment]

    /**
     * Replaces <code>n</code>-th element with <code>_a</code>.
     *
     * @pre `n in [0, size)`.
     */
    final  def replace[n <: Nat, e <: Any](n: n, e: e): replace[n, e] = take(n) ::: _cons_drop(n, e)
    final type replace[n <: Nat, e <: Any] = take[n] ::: _cons_drop[n, e]

    @compilerWorkaround("2.8.0") // works around a type mismatch.
    private final  def _cons_drop[n <: Nat, e <: Any](n: n, e: e): _cons_drop[n, e] = Cons(e, drop(n.increment))
    private final type _cons_drop[n <: Nat, e <: Any] = Cons[e, drop[n#increment]]

    /**
     * Prepends reversed <code>that</code>.
     */
    final  def prependReversed[that <: List](that: that) = new PrependReversed().apply(self, that)
    final type prependReversed[that <: List] = PrependReversed#apply[self, that]

    /**
     * Returns reversed one.
     */
    final  def reverse: reverse = Nil.prependReversed(self)
    final type reverse = Nil#prependReversed[self]

    /**
     * Returns the size.
     */
    final  def size: size = new Size().apply(self)
    final type size = Size#apply[self]

    /**
     * Zips <code>that</code>.
     *
     * @pre <code>size &lt;= that.size<code>.
     */
     def zip[that <: List](that: that): zip[that]
    type zip[that <: List] <: List

    @aliasOf("addFirst")
    final def ::[e <: Any](e: e): addFirst[e] = Cons(e, self)

    @aliasOf("prepend")
    final def :::[that <: List](that: that): prepend[that] = prepend(that)

    @aliasOf("prependReversed")
    final def reverse_:::[that <: List](that: that): prependReversed[that] = prependReversed(that)

    /**
     * Returns the first element whose type is <code>k</code>.
     *
     * @pre List contains an element whose type is <code>k</code>.
     */
    final  def elementOf[e <: Any](implicit _elementOf: ElementOf[self, e]): elementOf[e] = _elementOf(self)
    final type elementOf[e <: Any] = ElementOf.result[self, e]

    /**
     * Converts to <code>sequence.List[Any]</code>.
     */
    def untyped: untyped
    final type untyped = sequence.List[Any]

    final override def equals(that: Any) = that match {
        case that: List => untyped == that.untyped
        case _ => false
    }
    final override def hashCode = untyped.hashCode
    final override def toString = untyped.toString

    /**
     * Folds right-associative.
     */
     def foldRight_Any[z <: Any, f <: Function2_Any_Any_Any](z: z, f: f): foldRight_Any[z, f]
    type foldRight_Any[z <: Any, f <: Function2_Any_Any_Any] <: Any

    /**
     * Folds right-associative.
     */
     def foldRight_List[z <: List, f <: Function2_Any_List_List](z: z, f: f): foldRight_List[z, f]
    type foldRight_List[z <: List, f <: Function2_Any_List_List] <: List

    /**
     * Folds right-associative.
     */
     def foldRight_Nat[z <: Nat, f <: Function2_Any_Nat_Nat](z: z, f: f): foldRight_Nat[z, f]
    type foldRight_Nat[z <: Nat, f <: Function2_Any_Nat_Nat] <: Nat

    /**
     * Folds left-associative.
     */
     def foldLeft_List[z <: List, f <: Function2_List_Any_List](z: z, f: f): foldLeft_List[z, f]
    type foldLeft_List[z <: List, f <: Function2_List_Any_List] <: List

}


sealed abstract class Nil extends List {
    override  val self = this
    override type self = Nil

    override  def head = unsupported
    override type head = unsupported

    override  def tail = unsupported // `Nil` would make `List.take` less-restrictive?
    override type tail = unsupported

    override  def isEmpty = `true`
    override type isEmpty = `true`

    override  def zip[that <: List](that: that) = Nil
    override type zip[that <: List] = Nil

    override def untyped = sequence.Nil

    override  def foldRight_Any[z <: Any, f <: Function2_Any_Any_Any](z: z, f: f): foldRight_Any[z, f] = z
    override type foldRight_Any[z <: Any, f <: Function2_Any_Any_Any] = z

    override  def foldRight_List[z <: List, f <: Function2_Any_List_List](z: z, f: f): foldRight_List[z, f] = z
    override type foldRight_List[z <: List, f <: Function2_Any_List_List] = z

    override  def foldRight_Nat[z <: Nat, f <: Function2_Any_Nat_Nat](z: z, f: f): foldRight_Nat[z, f] = z
    override type foldRight_Nat[z <: Nat, f <: Function2_Any_Nat_Nat] = z

    override  def foldLeft_List[z <: List, f <: Function2_List_Any_List](z: z, f: f): foldLeft_List[z, f] = z
    override type foldLeft_List[z <: List, f <: Function2_List_Any_List] = z
}


final case class Cons[x <: Any, xs <: List](x: x, xs: xs) extends List {
    override  val self = this
    override type self = Cons[x, xs]

    override  val head = x
    override type head = x

    override  val tail = xs
    override type tail = xs

    override  def isEmpty = `false`
    override type isEmpty = `false`

    override  def zip[that <: List](that: that): zip[that] = Cons(scala.Tuple2(head, that.head), tail.zip(that.tail))
    override type zip[that <: List] = Cons[scala.Tuple2[head, that#head], tail#zip[that#tail]]

    override def untyped = head :: tail.untyped

    override  def foldRight_Any[z <: Any, f <: Function2_Any_Any_Any](z: z, f: f): foldRight_Any[z, f] = f.apply(head, tail.foldRight_Any(z, f))
    override type foldRight_Any[z <: Any, f <: Function2_Any_Any_Any] = f#apply[head, tail#foldRight_Any[z, f]]

    override  def foldRight_List[z <: List, f <: Function2_Any_List_List](z: z, f: f): foldRight_List[z, f] = f.apply(head, tail.foldRight_List(z, f))
    override type foldRight_List[z <: List, f <: Function2_Any_List_List] = f#apply[head, tail#foldRight_List[z, f]]

    override  def foldRight_Nat[z <: Nat, f <: Function2_Any_Nat_Nat](z: z, f: f): foldRight_Nat[z, f] = f.apply(head, tail.foldRight_Nat(z, f))
    override type foldRight_Nat[z <: Nat, f <: Function2_Any_Nat_Nat] = f#apply[head, tail#foldRight_Nat[z, f]]

    override  def foldLeft_List[z <: List, f <: Function2_List_Any_List](z: z, f: f): foldLeft_List[z, f] = tail.foldLeft_List(f.apply(z, head), f)
    override type foldLeft_List[z <: List, f <: Function2_List_Any_List] = tail#foldLeft_List[f#apply[z, head], f]
}


object List {

    private[mada] val _Nil = new Nil{}


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

    def unapply[x <: Any, xs <: List](xs: Cons[x, xs]): Option[(x, xs)] = Some(xs.head, xs.tail)

}
