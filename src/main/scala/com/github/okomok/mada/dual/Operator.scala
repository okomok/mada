

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
    type +[a <: Operatable_+, b <: a#Operand_+] = a# +[b]
    type -[a <: Operatable_-, b <: a#Operand_-] = a# -[b]
    type x[a <: Operatable_x, b <: a#Operand_x] = a# x[b]

    type ===[a <: Operatable_===, b <: a#Operand_===] = a# ===[b]
    type !==[a <: Operatable_===, b <: a#Operand_===] = a# ===[b]#not

    type &&[a <: Operatable_&&, b <: a#Operand_&&] = a# &&[b]
    type ||[a <: Operatable_||, b <: a#Operand_||] = a# ||[b]

    type <[a <: Operatable_<, b <: a#Operand_<] = a# <[b]
    type <=[a <: Operatable_<=, b <: a#Operand_<=] = a# <=[b]
    type >[a <: Operatable_>, b <: a#Operand_>] = a# >[b]
    type >=[a <: Operatable_>=, b <: a#Operand_>=] = a# >=[b]
}


// Operatables

trait Operatable_=== {
    type Operand_===
    def ===[that <: Operand_===](that: that): ===[that]
    type ===[that <: Operand_===] <: Boolean
}


trait Operatable_+ {
    type Operand_+
    def +[that <: Operand_+](that: that): +[that]
    type +[that <: Operand_+]
}

trait Operatable_- {
    type Operand_-
    def -[that <: Operand_-](that: that): -[that]
    type -[that <: Operand_-]
}

trait Operatable_x {
    type Operand_x
    def x[that <: Operand_x](that: that): x[that]
    type x[that <: Operand_x]
}


trait Operatable_&& {
    type Operand_&&
    def &&[that <: Operand_&&](that: that): &&[that]
    type &&[that <: Operand_&&]
}

trait Operatable_|| {
    type Operand_||
    def ||[that <: Operand_||](that: that): ||[that]
    type ||[that <: Operand_||]
}


trait Operatable_< {
    type Operand_<
    def <[that <: Operand_<](that: that): <[that]
    type <[that <: Operand_<]
}

trait Operatable_<= {
    type Operand_<=
    def <=[that <: Operand_<=](that: that): <=[that]
    type <=[that <: Operand_<=]
}

trait Operatable_> {
    type Operand_>
    def >[that <: Operand_>](that: that): >[that]
    type >[that <: Operand_>]
}

trait Operatable_>= {
    type Operand_>=
    def >=[that <: Operand_>=](that: that): >=[that]
    type >=[that <: Operand_>=]
}
