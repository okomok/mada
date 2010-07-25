

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


object List extends Common with ToSTuple


sealed abstract class List extends Any {
    type self <: List
    type asInstanceOfList = self

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

    final  def flatMap[f <: Function1](f: f): flatMap[f] = map(f).flatten
    final type flatMap[f <: Function1] = map[f]#flatten

     def flatten: flatten
    type flatten <: List

     def filter[f <: Function1](f: f): filter[f]
    type filter[f <: Function1] <: List

     def partition[f <: Function1](f: f): partition[f]
    type partition[f <: Function1] <: Product2

     def sort[o <: Ordering](o: o): sort[o]
    type sort[o <: Ordering] <: List

    final  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final type forall[f <: Function1] = exists[f#not]#not

    final  def exists[f <: Function1](f: f): exists[f] = find(f).nonEmpty
    final type exists[f <: Function1] = find[f]#nonEmpty

    final  def count[f <: Function1](f: f): count[f] = new Count().apply(self, f)
    final type count[f <: Function1] = Count#apply[self, f]

     def find[f <: Function1](f: f): find[f]
    type find[f <: Function1] <: Option

     def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f]
    type foldLeft[z <: Any, f <: Function2] <: Any

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

     def reduceLeft[f <: Function2](f: f): reduceLeft[f]
    type reduceLeft[f <: Function2] <: Any

     def reduceRight[f <: Function2](f: f): reduceRight[f]
    type reduceRight[f <: Function2] <: Any

     def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f]
    type scanLeft[z <: Any, f <: Function2] <: List

     def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f]
    type scanRight[z <: Any, f <: Function2] <: List

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

     def init: init
    type init <: List

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

     def span[f <: Function1](f: f): span[f]
    type span[f <: Function1] <: Product2

     def splitAt[n <: Nat](n: n): splitAt[n]
    type splitAt[n <: Nat] <: Product2

     def equivTo[that <: List, e <: Equiv](that: that, e: e): equivTo[that, e]
    type equivTo[that <: List, e <: Equiv] <: Boolean

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
     */
    @pre("List contains an element whose type is `k`.")
    final  def elementOf[e <: Any](implicit _elementOf: ElementOf[self, e]): elementOf[e] = _elementOf(self)
    final type elementOf[e <: Any] = ElementOf.result[self, e]

    final override type undual = scala.collection.immutable.List[scala.Any]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[List]
}


sealed abstract class Nil extends List {
    type self = Nil

    override  def head: head = `throw`(new NoSuchElementException("dual.list.Nil.head"))
    override type head = `throw`[_]

    override  def tail: tail = `throw`(new NoSuchElementException("dual.list.Nil.tail"))
    override type tail = `throw`[_]

    override  def foreach[f <: Function1](f: f): foreach[f] = Unit

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def size: size = nat.peano.Zero
    override type size = nat.peano.Zero

    override private[mada]  def append[that <: List](that: that): append[that] = that
    override private[mada] type append[that <: List] = that

    override  def map[f <: Function1](f: f): map[f] = self
    override type map[f <: Function1] = self

    override  def flatten: flatten = self
    override type flatten = self

    override  def filter[f <: Function1](f: f): filter[f] = self
    override type filter[f <: Function1] = self

    override  def partition[f <: Function1](f: f): partition[f] = Tuple2(self, self)
    override type partition[f <: Function1] = Tuple2[self, self]

    override  def sort[o <: Ordering](o: o): sort[o] = self
    override type sort[o <: Ordering] = self

    override  def find[f <: Function1](f: f): find[f] = None
    override type find[f <: Function1] = None

    override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = z
    override type foldLeft[z <: Any, f <: Function2] = z

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2] = z

    override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = unsupported("list.Nil.reduceLeft")
    override type reduceLeft[f <: Function2] = unsupported[_]

    override  def reduceRight[f <: Function2](f: f): reduceRight[f] = unsupported("list.Nil.reduceRight")
    override type reduceRight[f <: Function2] = unsupported[_]

    override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = Cons(z, self)
    override type scanLeft[z <: Any, f <: Function2] = Cons[z, self]

    override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = Cons(z, self)
    override type scanRight[z <: Any, f <: Function2] = Cons[z, self]

    override  def nthOption[n <: Nat](n: n): nthOption[n] = None
    override type nthOption[n <: Nat] = None

    override  def init: init = unsupported("list.Nil.init")
    override type init = unsupported[_]

    override  def takeWhile[f <: Function1](f: f): takeWhile[f] = self
    override type takeWhile[f <: Function1] = self

    override  def dropWhile[f <: Function1](f: f): dropWhile[f] = self
    override type dropWhile[f <: Function1] = self

    override  def span[f <: Function1](f: f): span[f] = Tuple2(self, self)
    override type span[f <: Function1] = Tuple2[self, self]

    override  def splitAt[n <: Nat](n: n): splitAt[n] = Tuple2(self, self)
    override type splitAt[n <: Nat] = Tuple2[self, self]

    override  def equivTo[that <: List, e <: Equiv](that: that, e: e): equivTo[that, e] = that.isEmpty
    override type equivTo[that <: List, e <: Equiv] = that#isEmpty

    override private[mada]  def reverseAppend[that <: List](that: that) = that
    override private[mada] type reverseAppend[that <: List] = that

    override  def zip[that <: List](that: that): zip[that] = self
    override type zip[that <: List] = self

    override  def undual: undual = scala.collection.immutable.Nil
}


final case class Cons[x <: Any, xs <: List](override val head: x, override val tail: xs) extends List {
    type self = Cons[x, xs]

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

    override  def flatten: flatten = tail.flatten.:::(head.asInstanceOfList)
    override type flatten = tail#flatten# :::[head#asInstanceOfList]

    override  def filter[f <: Function1](f: f): filter[f] = new ConsFilter().apply(head, tail, f)
    override type filter[f <: Function1] = ConsFilter#apply[head, tail, f]

    override  def partition[f <: Function1](f: f): partition[f] = new ConsPartition().apply(head, tail, f)
    override type partition[f <: Function1] = ConsPartition#apply[head, tail, f]

    override  def sort[o <: Ordering](o: o): sort[o] = new ConsSort().apply(self, o)
    override type sort[o <: Ordering] = ConsSort#apply[self, o]

    override  def find[f <: Function1](f: f): find[f] = new ConsFind().apply(head, tail, f)
    override type find[f <: Function1] = ConsFind#apply[head, tail, f]

    override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = tail.foldLeft(f.apply(z, head), f)
    override type foldLeft[z <: Any, f <: Function2] = tail#foldLeft[f#apply[z, head], f]

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(head, tail.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[head, tail#foldRight[z, f]]

    override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = tail.foldLeft(head, f)
    override type reduceLeft[f <: Function2] = tail#foldLeft[head, f]

    override  def reduceRight[f <: Function2](f: f): reduceRight[f] = tail.foldRight(head, f)
    override type reduceRight[f <: Function2] = tail#foldRight[head, f]

    override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = Cons(z, tail.scanLeft(f.apply(z, head), f))
    override type scanLeft[z <: Any, f <: Function2] = Cons[z, tail#scanLeft[f#apply[z, head], f]]

    override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = new ConsScanRight().apply(head, tail, z, f)
    override type scanRight[z <: Any, f <: Function2] = ConsScanRight#apply[head, tail, z, f]

    override  def nthOption[n <: Nat](n: n): nthOption[n] = new ConsNthOption().apply(head, tail, n)
    override type nthOption[n <: Nat] = ConsNthOption#apply[head, tail, n]

    override  def init: init = new ConsInit().apply(head, tail)
    override type init = ConsInit#apply[head, tail]

    override  def takeWhile[f <: Function1](f: f): takeWhile[f] = new ConsTakeWhile().apply(head, tail, f)
    override type takeWhile[f <: Function1] = ConsTakeWhile#apply[head, tail, f]

    override  def dropWhile[f <: Function1](f: f): dropWhile[f] = new ConsDropWhile().apply(self, f)
    override type dropWhile[f <: Function1] = ConsDropWhile#apply[self, f]

    override  def span[f <: Function1](f: f): span[f] = new ConsSpan().apply(self, f)
    override type span[f <: Function1] = ConsSpan#apply[self, f]

    override  def splitAt[n <: Nat](n: n): splitAt[n] = new ConsSplitAt().apply(self, n)
    override type splitAt[n <: Nat] = ConsSplitAt#apply[self, n]

    override  def equivTo[that <: List, e <: Equiv](that: that, e: e) = new ConsEquivTo().apply(self, that, e)
    override type equivTo[that <: List, e <: Equiv] = ConsEquivTo#apply[self, that, e]

    override private[mada]  def reverseAppend[that <: List](that: that) = tail.reverseAppend(Cons(head, that))
    override private[mada] type reverseAppend[that <: List] = tail#reverseAppend[Cons[head, that]]

    override  def zip[that <: List](that: that): zip[that] = Cons(Tuple2(head, that.head), tail.zip(that.tail))
    override type zip[that <: List] = Cons[Tuple2[head, that#head], tail#zip[that#tail]]

    override  def undual: undual = head.undual :: tail.undual
}


private[mada] object _List {
    val Nil = new Nil{}
}
