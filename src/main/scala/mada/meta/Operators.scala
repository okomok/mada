

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Contains "overloadable" operators.
 */
trait Operators { this: Meta.type =>

    // Just remove type parameters from SLS 5.3.3 Comparable.

    trait Operatable_+ {
        type Self <: Operatable_+
        type operate_+[that <: Self] <: Self
    }
    type +[a <: Operatable_+, b <: a#Self] = a#operate_+[b]

    trait Operatable_== {
        type Self <: Operatable_==
        type operate_==[that <: Self] <: Boolean
    }
    type ==[a <: Operatable_==, b <: a#Self] = a#operate_==[b]
    type !=[a <: Operatable_==, b <: a#Self] = a#operate_==[b]#not

    trait Operatable_++ {
        type Self <: Operatable_++
        type operate_++ <: Self
    }
    type ++[a <: Operatable_++] = a#operate_++

    trait Operatable_-- {
        type Self <: Operatable_--
        type operate_-- <: Self
    }
    type --[a <: Operatable_--] = a#operate_--
}
