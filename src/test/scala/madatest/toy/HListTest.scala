

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package hlisttest


sealed abstract class List[+A] { // this: self =>

    private val self = this.asInstanceOf[self]
    type self <: List[A]

    def head: head
    type head <: A

    def tail: tail
    type tail <: List[A]

    def ::[B >: A](e: B): Cons[B, B, self] = Cons(e, self)

}


// Compiler will fail to search implicits.
// case object Nil; type Nil = Nil.type

sealed abstract class Nil extends List[Nothing] {
    override type self = this.type

    override def head = throw new NoSuchElementException("head of empty list")
    override type head = Nothing
    override def tail = throw new NoSuchElementException("tail of empty list")
    override type tail = Nothing
}

object NilWrap {
    val value: Nil = new Nil{}
}


final case class Cons[+A, h <: A, t <: List[A]](override val head: h, override val tail: t) extends List[A] {
    override type self = this.type

    override type head = h
    override type tail = t
}


object List {

    val Nil = NilWrap.value

    type ::[A, h <: A, t <: List[A]] = Cons[A, h, t]

}


class HListTest {

    import List._

    trait E {
        val l0: Nil = Nil
        type l1 = Cons[Char, Char, Nil.type]
        // val l1_ : l1 = 'c' :: l0 // error
        val l1: l1 = 'c' :: Nil
        type l2 = Cons[AnyVal, AnyVal, l1.type]
        val l2: l2 = 123 :: l1
        type l3 = Cons[Any, Any, l2.type]
        val l3 = "string" :: l2
    }

    trait I {
        val l0 = Nil
        val l1 = 'c' :: l0
        val l2 = 123 :: l1
        val l3 = "string" :: l2
    }

    trait Wow {
//        val l3 = "string" :: 123 :: 'c' :: Nil // error
//        val l4 = "string" :: (123 :: ('c' :: Nil)) // error
    }

}
