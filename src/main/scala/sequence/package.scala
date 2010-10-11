

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object sequence {

    @aliasOf("iterative.Iterative")
     val Iterative = iterative.Iterative
    type Iterative[+A] = iterative.Iterative[A]

    @aliasOf("iterator.Iterator")
     val Iterator = iterator.Iterator
    type Iterator[+A] = iterator.Iterator[A]

    @aliasOf("list.List")
     val List = list.List
    type List[+A] = list.List[A]

    @aliasOf("list.Nil")
    val Nil = list.Nil

    @aliasOf("list.Cons")
    type Cons[+A] = list.Cons[A]

    @aliasOf("list.::")
    val :: = list.::

    @aliasOf("list.#::")
    val #:: = list.#::

    @aliasOf("reactive.Reactive")
     val Reactive = reactive.Reactive
    type Reactive[+A] = reactive.Reactive[A]

    @aliasOf("vector.Vector")
     val Vector = vector.Vector
    type Vector[+A] = vector.Vector[A]

}
