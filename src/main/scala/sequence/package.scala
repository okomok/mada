

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object sequence {

    @annotation.aliasOf("iterative.Iterative")
     val Iterative = iterative.Iterative
    type Iterative[+A] = iterative.Iterative[A]

    @annotation.aliasOf("iterator.Iterator")
     val Iterator = iterator.Iterator
    type Iterator[+A] = iterator.Iterator[A]
    type _Iterator[+A] = iterator._Iterator[A]

    @annotation.aliasOf("list.List")
     val List = list.List
    type List[+A] = list.List[A]

    @annotation.aliasOf("list.Nil")
    val Nil = list.Nil

    @annotation.aliasOf("list.Cons")
    type Cons[+A] = list.Cons[A]

    @annotation.aliasOf("list.::")
    val :: = list.::

    @annotation.aliasOf("list.#::")
    val #:: = list.#::

    @annotation.aliasOf("reactive.Reactive")
     val Reactive = reactive.Reactive
    type Reactive[+A] = reactive.Reactive[A]

    @annotation.aliasOf("vector.Vector")
     val Vector = vector.Vector
    type Vector[+A] = vector.Vector[A]

}
