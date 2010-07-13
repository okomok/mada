

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


object List extends Common {

// methodization

    sealed class _Of1[_this <: List](_this: _this) {
        def toSTuple: scala.Tuple1[_this#head#undual] = scala.Tuple1(_this.head.undual)
    }
    implicit def _of1[a1 <: Any](_this: a1 :: Nil): _Of1[a1 :: Nil] = new _Of1(_this)

    sealed class _Of2[_this <: List](_this: _this) {
        def toSTuple: scala.Tuple2[_this#head#undual, _this#tail#head#undual] = scala.Tuple2(_this.head.undual, _this.tail.head.undual)
    }
    implicit def _of2[a1 <: Any, a2 <: Any](_this: a1 :: a2 :: Nil): _Of2[a1 :: a2 :: Nil] = new _Of2(_this)

    sealed class _Of3[_this <: List](_this: _this) {
        def toSTuple: scala.Tuple3[_this#head#undual, _this#tail#head#undual, _this#tail#tail#head#undual] = scala.Tuple3(_this.head.undual, _this.tail.head.undual, _this.tail.tail.head.undual)
    }
    implicit def _of3[a1 <: Any, a2 <: Any, a3 <: Any](_this: a1 :: a2 :: a3 :: Nil): _Of3[a1 :: a2 :: a3 :: Nil] = new _Of3(_this)

    sealed class _Of4[_this <: List](_this: _this) {
        def toSTuple: scala.Tuple4[_this#head#undual, _this#tail#head#undual, _this#tail#tail#head#undual, _this#tail#tail#tail#head#undual] = scala.Tuple4(_this.head.undual, _this.tail.head.undual, _this.tail.tail.head.undual, _this.tail.tail.tail.head.undual)
    }
    implicit def _of4[a1 <: Any, a2 <: Any, a3 <: Any, a4 <: Any](_this: a1 :: a2 :: a3 :: a4 :: Nil): _Of4[a1 :: a2 :: a3 :: a4 :: Nil] = new _Of4(_this)

    sealed class _Of5[_this <: List](_this: _this) {
        def toSTuple: scala.Tuple5[_this#head#undual, _this#tail#head#undual, _this#tail#tail#head#undual, _this#tail#tail#tail#head#undual, _this#tail#tail#tail#tail#head#undual] = scala.Tuple5(_this.head.undual, _this.tail.head.undual, _this.tail.tail.head.undual, _this.tail.tail.tail.head.undual, _this.tail.tail.tail.tail.head.undual)
    }
    implicit def _of5[a1 <: Any, a2 <: Any, a3 <: Any, a4 <: Any, a5 <: Any](_this: a1 :: a2 :: a3 :: a4 :: a5 :: Nil): _Of5[a1 :: a2 :: a3 :: a4 :: a5 :: Nil] = new _Of5(_this)

}


sealed abstract class List extends Any {

    type self <: List

    final override  def asInstanceOfList = self
    final override type asInstanceOfList = self

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
     * Maps elements using <code>f</code>.
     */
     def map[f <: Function1](f: f): map[f]
    type map[f <: Function1] <: List

    /**
     * Flattens elements using <code>f</code>.
     */
     def flatMap[f <: Function1](f: f): flatMap[f]
    type flatMap[f <: Function1] <: List

    /**
     * Filters elements using <code>f</code>.
     */
     def filter[f <: Function1](f: f): filter[f]
    type filter[f <: Function1] <: List

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach[f <: Function1](f: f): foreach[f]
    final type foreach[f <: Function1] = Unit

    /**
     * Drops at most <code>n</code> elements.
     *
     * @pre `n in [0, size)`.
     */
    final  def drop[n <: nat.Peano](n: n): drop[n] = Drop(self, n).apply//new Drop().apply(self, n)
    final type drop[n <: nat.Peano] = Drop[self, n]#apply//Drop#apply[self, n]

    /**
     * Takes at most <code>n</code> elements.
     *
     * @pre `n in [0, size)`.
     */
    final  def take[n <: nat.Peano](n: n): take[n] = Take(self, n).apply//reverse.drop(size - n).reverse.asInstanceOf[take[n]] //Take.apply(self, n)
    final type take[n <: nat.Peano] = Take[self, n]#apply//reverse#drop[size# -[n]]#reverse //Take.apply[self, n]

    @equivalentTo("take(m).drop(n)")
    final  def slice[n <: nat.Peano, m <: nat.Peano](n: n, m: m): slice[n, m] = take(m).drop(n)
    final type slice[n <: nat.Peano, m <: nat.Peano] = take[m]#drop[n]

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
    final  def insert[n <: nat.Peano, that <: List](n: n, that: that): insert[n, that] = take(n) ::: that ::: drop(n)
    final type insert[n <: nat.Peano, that <: List] = take[n] ::: that ::: drop[n]

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
    final  def nth[n <: nat.Peano](n: n): nth[n] = drop(n).head
    final type nth[n <: nat.Peano] = drop[n]#head

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
    final  def remove[n <: nat.Peano](n: n): remove[n] = (take(n) ::: drop(n.increment)).asInstanceOf[remove[n]]
    final type remove[n <: nat.Peano] = take[n] ::: drop[n#increment]

    /**
     * Replaces <code>n</code>-th element with <code>_a</code>.
     *
     * @pre `n in [0, size)`.
     */
    final  def replace[n <: nat.Peano, e <: Any](n: n, e: e): replace[n, e] = (take(n) ::: Cons(e, drop(n.increment))).asInstanceOf[replace[n, e]]
    final type replace[n <: nat.Peano, e <: Any] = take[n] ::: Cons[e, drop[n#increment]]

    /**
     * Prepends reversed <code>that</code>.
     */
    final  def prependReversed[that <: List](that: that): prependReversed[that] = new PrependReversed().apply(self, that)
    final type prependReversed[that <: List] = PrependReversed#apply[self, that]

    /**
     * Returns reversed one.
     */
    final  def reverse: reverse = Nil.prependReversed(self)
    final type reverse = Nil#prependReversed[self]

    /**
     * Returns the size.
     */
     def size: size
    type size <: nat.Peano

    /**
     * Zips <code>that</code>.
     */
     def zip[that <: List](that: that): zip[that]
    type zip[that <: List] <: List

    /**
     * Unzips.
     */
    final  def unzip: unzip = Unzip(self).apply
    final type unzip = Unzip[self]#apply

    @aliasOf("addFirst")
    final def ::[e <: Any](e: e): addFirst[e] = addFirst(e)

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
     * Folds right-associative.
     */
     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

    /**
     * Folds left-associative.
     */
     def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f]
    type foldLeft[z <: Any, f <: Function2] <: Any

    final override type undual = sequence.List[scala.Any]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[List]
}


sealed abstract class Nil extends List {
    override  val self = this
    override type self = Nil

    override  def head: head = `throw`(new scala.NoSuchElementException)
    override type head = `throw`[scala.NoSuchElementException]

    override  def tail: tail = `throw`(new scala.NoSuchElementException)
    override type tail = `throw`[scala.NoSuchElementException]

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def map[f <: Function1](f: f): map[f] = Nil
    override type map[f <: Function1] = Nil

    override  def flatMap[f <: Function1](f: f): flatMap[f] = Nil
    override type flatMap[f <: Function1] = Nil

    override  def filter[f <: Function1](f: f): filter[f] = Nil
    override type filter[f <: Function1] = Nil

    override  def foreach[f <: Function1](f: f): foreach[f] = Unit

    override  def size: size = nat.peano.Zero
    override type size = nat.peano.Zero

    override  def zip[that <: List](that: that): zip[that] = Nil
    override type zip[that <: List] = Nil

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2] = z

    override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = z
    override type foldLeft[z <: Any, f <: Function2] = z

    override def undual: undual = sequence.Nil
}


final case class Cons[x <: Any, xs <: List](private val x: x, private val xs: xs) extends List {
    override  val self = this
    override type self = Cons[x, xs]

    override  val head: head = x
    override type head = x

    override  val tail: tail = xs
    override type tail = xs

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  def map[f <: Function1](f: f): map[f] = Cons(f.apply(x), xs.map(f))
    override type map[f <: Function1] = Cons[f#apply[x], xs#map[f]]

    override  def flatMap[f <: Function1](f: f): flatMap[f] = f.apply(x).asInstanceOfList ::: xs.flatMap(f)
    override type flatMap[f <: Function1] = f#apply[x]#asInstanceOfList ::: xs#flatMap[f]

    override  def filter[f <: Function1](f: f): filter[f] = new FilterCons().apply(x, xs, f)
    override type filter[f <: Function1] = FilterCons#apply[x, xs, f]

    override  def foreach[f <: Function1](f: f): foreach[f] = { f.apply(x); xs.foreach(f) }

    override  def size: size = xs.size.increment
    override type size = xs#size#increment

    override  def zip[that <: List](that: that): zip[that] = Cons(Tuple2(head, that.head), tail.zip(that.tail))
    override type zip[that <: List] = Cons[Tuple2[head, that#head], tail#zip[that#tail]]

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(head, tail.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[head, tail#foldRight[z, f]]

    override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = tail.foldLeft(f.apply(z, head), f)
    override type foldLeft[z <: Any, f <: Function2] = tail#foldLeft[f#apply[z, head], f]

    override def undual: undual = head.undual :: tail.undual
}


private[mada] object _List {
    val Nil = new Nil{}
}
