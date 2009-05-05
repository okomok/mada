

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Contains "overloadable" operators.
 */
@provider
trait Operators { this: Meta.type =>

    @returnthis val Operators: Operators = this

    type +[a <: Operatable_+, b <: a#Operand_+] = a#operator_+[b]
    type -[a <: Operatable_-, b <: a#Operand_-] = a#operator_-[b]
    type *[a <: Operatable_*, b <: a#Operand_*] = a#operator_*[b]
    type /[a <: Operatable_/, b <: a#Operand_/] = a#operator_/[b]

    type ==[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]
    type !=[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]#not

    type &&[a <: Operatable_&&, b <: a#Operand_&&] = a#operator_&&[b]
    type ||[a <: Operatable_||, b <: a#Operand_||] = a#operator_||[b]

    type ![a <: Operatable_!] = a#operator_!
    type ++[a <: Operatable_++] = a#operator_++
    type --[a <: Operatable_--] = a#operator_--

    trait Operatable extends Operatable_== with Operatable_+ with Operatable_- with Operatable_* with Operatable_/
        with Operatable_&& with Operatable_||
        with Operatable_++ with Operatable_-- with Operatable_!

    trait Operatable_== {
        type Operand_==
        type operator_==[that <: Operand_==] <: Boolean
    }

    trait Operatable_+ {
        type Operand_+
        type operator_+[that <: Operand_+]
    }

    trait Operatable_- {
        type Operand_-
        type operator_-[that <: Operand_-]
    }

    trait Operatable_* {
        type Operand_*
        type operator_*[that <: Operand_*]
    }

    trait Operatable_/ {
        type Operand_/
        type operator_/[that <: Operand_/]
    }

    trait Operatable_&& {
        type Operand_&&
        type operator_&&[that <: Operand_&&]
    }

    trait Operatable_|| {
        type Operand_||
        type operator_||[that <: Operand_||]
    }

    trait Operatable_++ {
        type operator_++
    }

    trait Operatable_-- {
        type operator_--
    }

    trait Operatable_! {
        type operator_!
    }
}

