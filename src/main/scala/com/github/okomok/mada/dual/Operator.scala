

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


// See: Utils.scala
//      at http://www.assembla.com/wiki/show/metascala


// Operator

/**
 * Contains infix operators for dual types.
 */
object Operator extends OperatorCommon

private[mada] trait OperatorCommon {
    type +[a <: Operatable_+, b <: a#Operand_+] = a#operator_+[b]
    type -[a <: Operatable_-, b <: a#Operand_-] = a#operator_-[b]
    //type *[a <: Operatable_*, b <: a#Operand_*] = a#operator_*[b]

    type ==[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]
    type !=[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]#not

    type &&[a <: Operatable_&&, b <: a#Operand_&&] = a#operator_&&[b]
    type ||[a <: Operatable_||, b <: a#Operand_||] = a#operator_||[b]

    type <[a <: Operatable_<, b <: a#Operand_<] = a#operator_<[b]
    type <=[a <: Operatable_<=, b <: a#Operand_<=] = a#operator_<=[b]
    type >[a <: Operatable_>, b <: a#Operand_>] = a#operator_>[b]
    type >=[a <: Operatable_>=, b <: a#Operand_>=] = a#operator_>=[b]
}


// Operatables

trait Operatable extends Operatable_==
    with Operatable_+ with Operatable_- with Operatable_*
    with Operatable_&& with Operatable_||
    with Operatable_< with Operatable_<= with Operatable_> with Operatable_>=


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


trait Operatable_&& {
    type Operand_&&
    type operator_&&[that <: Operand_&&]
}

trait Operatable_|| {
    type Operand_||
    type operator_||[that <: Operand_||]
}


trait Operatable_< {
    type Operand_<
    type operator_<[that <: Operand_<]
}

trait Operatable_<= {
    type Operand_<=
    type operator_<=[that <: Operand_<=]
}

trait Operatable_> {
    type Operand_>
    type operator_>[that <: Operand_>]
}

trait Operatable_>= {
    type Operand_>=
    type operator_>=[that <: Operand_>=]
}
