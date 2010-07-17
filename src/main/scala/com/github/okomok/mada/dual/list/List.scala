

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

     def head: head
    type head <: Any

     def tail: tail
    type tail <: List

    @aliasOf("addFirst")
    final def ::[e <: Any](e: e): addFirst[e] = addFirst(e)

    @equivalentTo("Cons(e, self)")
    final  def addFirst[e <: Any](e: e): addFirst[e] = Cons(e, self)
    final type addFirst[e <: Any] = Cons[e, self]

    def foreach[f <: Function1](f: f): foreach[f]
    final type foreach[f <: Function1] = Unit

     def isEmpty: isEmpty
    type isEmpty <: Boolean

    final  def nonEmpty: nonEmpty = isEmpty.not
    final type nonEmpty = isEmpty#not

     def size: size
    type size <: Nat

    final  def length: length = size
    final type length = size

    final  def :::[that <: List](that: that): :::[that] = that.append(self)
    final type :::[that <: List] = that#append[self]

    private[mada]  def append[that <: List](that: that): append[that]
    private[mada] type append[that <: List] <: List

     def map[f <: Function1](f: f): map[f]
    type map[f <: Function1] <: List

     def flatMap[f <: Function1](f: f): flatMap[f]
    type flatMap[f <: Function1] <: List

     def filter[f <: Function1](f: f): filter[f]
    type filter[f <: Function1] <: List

    final  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final type forall[f <: Function1] = exists[f#not]#not

    final  def exists[f <: Function1](f: f): exists[f] = find(f).nonEmpty
    final type exists[f <: Function1] = find[f]#nonEmpty

     def find[f <: Function1](f: f): find[f]
    type find[f <: Function1] <: Option

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

     def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f]
    type foldLeft[z <: Any, f <: Function2] <: Any

    final  def nth[n <: Nat](n: n): nth[n] = nthOption(n).get
    final type nth[n <: Nat] = nthOption[n]#get

     def nthOption[n <: Nat](n: n): nthOption[n]
    type nthOption[n <: Nat] <: Option

    final  def headOption: headOption = nthOption(nat.peano.Zero)
    final type headOption = nthOption[nat.peano.Zero]

    final  def last: last = nth(size.decrement)
    final type last = nth[size#decrement]

    final  def lastOption: lastOption = nthOption(size.decrement)
    final type lastOption = nthOption[size#decrement]

    final  def init: init = reverse.tail.reverse
    final type init = reverse#tail#reverse

    final  def take[n <: Nat](n: n): take[n] = new Take().apply(self, n)
    final type take[n <: Nat] = Take#apply[self, n]

    final  def drop[n <: Nat](n: n): drop[n] = new Drop().apply(self, n)
    final type drop[n <: Nat] = Drop#apply[self, n]

    final  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = take(m).drop(n)
    final type slice[n <: Nat, m <: Nat] = take[m]#drop[n]

     def takeWhile[f <: Function1](f: f): takeWhile[f]
    type takeWhile[f <: Function1] <: List

     def dropWhile[f <: Function1](f: f): dropWhile[f]
    type dropWhile[f <: Function1] <: List

    final  def reverse_:::[that <: List](that: that): reverse_:::[that] = that.reverseAppend(self)
    final type reverse_:::[that <: List] = that#reverseAppend[self]

    final  def reverse: reverse = reverseAppend(Nil)
    final type reverse = reverseAppend[Nil]

    private[mada]  def reverseAppend[that <: List](that: that): reverseAppend[that]
    private[mada] type reverseAppend[that <: List] <: List

     def zip[that <: List](that: that): zip[that]
    type zip[that <: List] <: List

    final  def unzip: unzip = new Unzip().apply(self)
    final type unzip = Unzip#apply[self]

    /**
     * Returns the first element whose type is <code>k</code>.
     *
     * @pre List contains an element whose type is <code>k</code>.
     */
    final  def elementOf[e <: Any](implicit _elementOf: ElementOf[self, e]): elementOf[e] = _elementOf(self)
    final type elementOf[e <: Any] = ElementOf.result[self, e]

    final override type undual = sequence.List[_]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[List]
}


sealed abstract class Nil extends List {
    override  val self = this
    override type self = Nil

    override  def head: head = `throw`(new scala.NoSuchElementException("dual.list.Nil.head"))
    override type head = `throw`[scala.NoSuchElementException]

    override  def tail: tail = `throw`(new scala.NoSuchElementException("dual.list.Nil.tail"))
    override type tail = `throw`[scala.NoSuchElementException]

    override  def foreach[f <: Function1](f: f): foreach[f] = Unit

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def size: size = nat.peano.Zero
    override type size = nat.peano.Zero

    override private[mada]  def append[that <: List](that: that): append[that] = that
    override private[mada] type append[that <: List] = that

    override  def map[f <: Function1](f: f): map[f] = self
    override type map[f <: Function1] = self

    override  def flatMap[f <: Function1](f: f): flatMap[f] = self
    override type flatMap[f <: Function1] = self

    override  def filter[f <: Function1](f: f): filter[f] = self
    override type filter[f <: Function1] = self

    override  def find[f <: Function1](f: f): find[f] = None
    override type find[f <: Function1] = None

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2] = z

    override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = z
    override type foldLeft[z <: Any, f <: Function2] = z

    override  def nthOption[n <: Nat](n: n): nthOption[n] = None
    override type nthOption[n <: Nat] = None

    override  def takeWhile[f <: Function1](f: f): takeWhile[f] = self
    override type takeWhile[f <: Function1] = self

    override  def dropWhile[f <: Function1](f: f): dropWhile[f] = self
    override type dropWhile[f <: Function1] = self

    override private[mada]  def reverseAppend[that <: List](that: that) = that
    override private[mada] type reverseAppend[that <: List] = that

    override  def zip[that <: List](that: that): zip[that] = self
    override type zip[that <: List] = self

    override  def undual: undual = sequence.Nil
}


final case class Cons[x <: Any, xs <: List](override val head: x, override val tail: xs) extends List {
    override  val self = this
    override type self = Cons[x, xs]

    override type head = x
    override type tail = xs

    override  def foreach[f <: Function1](f: f): foreach[f] = { f.apply(head); tail.foreach(f) }

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  val size: size = tail.size.increment
    override type size = tail#size#increment

    override private[mada]  def append[that <: List](that: that): append[that] = Cons(head, tail.append(that))
    override private[mada] type append[that <: List] = Cons[head, tail#append[that]]

    override  def map[f <: Function1](f: f): map[f] = Cons(f.apply(head), tail.map(f))
    override type map[f <: Function1] = Cons[f#apply[head], tail#map[f]]

    override  def flatMap[f <: Function1](f: f): flatMap[f] = tail.flatMap(f).:::(f.apply(head).asInstanceOfList)
    override type flatMap[f <: Function1] = tail#flatMap[f]# :::[f#apply[head]#asInstanceOfList]

    override  def filter[f <: Function1](f: f): filter[f] = new ConsFilter().apply(head, tail, f)
    override type filter[f <: Function1] = ConsFilter#apply[head, tail, f]

    override  def find[f <: Function1](f: f): find[f] = new ConsFind().apply(head, tail, f)
    override type find[f <: Function1] = ConsFind#apply[head, tail, f]

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(head, tail.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[head, tail#foldRight[z, f]]

    override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = tail.foldLeft(f.apply(z, head), f)
    override type foldLeft[z <: Any, f <: Function2] = tail#foldLeft[f#apply[z, head], f]

    override  def nthOption[n <: Nat](n: n): nthOption[n] = new ConsNthOption().apply(head, tail, n)
    override type nthOption[n <: Nat] = ConsNthOption#apply[head, tail, n]

    override  def takeWhile[f <: Function1](f: f): takeWhile[f] = new ConsTakeWhile().apply(head, tail, f)
    override type takeWhile[f <: Function1] = ConsTakeWhile#apply[head, tail, f]

    override  def dropWhile[f <: Function1](f: f): dropWhile[f] = new ConsDropWhile().apply(self, f)
    override type dropWhile[f <: Function1] = ConsDropWhile#apply[self, f]

    override private[mada]  def reverseAppend[that <: List](that: that) = tail.reverseAppend(Cons(head, that))
    override private[mada] type reverseAppend[that <: List] = tail#reverseAppend[Cons[head, that]]

    override  def zip[that <: List](that: that): zip[that] = Cons(Tuple2(head, that.head), tail.zip(that.tail))
    override type zip[that <: List] = Cons[Tuple2[head, that#head], tail#zip[that#tail]]

    override  def undual: undual = head.undual :: tail.undual
}


private[mada] object _List {
    val Nil = new Nil{}
}
