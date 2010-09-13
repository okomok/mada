

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


object Auto extends Common with Compatibles


/**
 * Trait for "automatic reference"
 * This is nothing but a tiny Traversable.
 */
trait Auto[+A] {

    @returnThis
    final def asAuto: Auto[A] = this

    def foreach(f: A => Unit): Unit

    def map[B](f: A => B): Auto[B] = Map(this, f)

    def flatMap[B](f: A => Auto[B]): Auto[B] = FlatMap(this, f)

    def filter(f: A => Boolean): Auto[A] = Filter(this, f)

    def append[B >: A](that: Auto[B]): Auto[B] = Append[B](this, that)

}
