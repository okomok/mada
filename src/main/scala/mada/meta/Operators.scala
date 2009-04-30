

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Contains "overloadable" operators.
 */
trait Operators { this: Meta.type =>

    type +[a <: Object, b <: a#This] = a#operator_+[b]
    type -[a <: Object, b <: a#This] = a#operator_-[b]

    type ==[a <: Object, b <: a#This] = a#operator_==[b]
    type !=[a <: Object, b <: a#This] = a#operator_==[b]#not

    type ++[a <: Object] = a#operator_++
    type --[a <: Object] = a#operator_--

}
