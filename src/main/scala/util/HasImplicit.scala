

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package util


@specializer
sealed abstract class HasImplicit[+A] extends Function0[Boolean]


object HasImplicit {

    implicit def _ofFound[A](implicit e: A) = new HasImplicit[A] {
        override val apply = true
    }

    val _ofNotFound = new HasImplicit[Nothing] {
        override val apply = false
    }

}
