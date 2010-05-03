

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package stack


class ToSome[A](val _1: Stack[A]) extends Forwarder[A] {
    override protected val delegate = _1

    override def toSome = _1.toSome // toSome-toSome fusion
}

object ToSome {
    implicit def madaStackCompatibleToSStack[A](from: ToSome[A]): scala.collection.mutable.Stack[A] = from._1.toSStack
}
