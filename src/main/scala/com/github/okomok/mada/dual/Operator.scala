

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


// See: Utils.scala
//      at http://www.assembla.com/wiki/show/metascala


// These are meta-generics, which doesn't work in complicated context.
// Used only as syntax sugar.


// Operator

/**
 * Contains infix operators for dual types.
 */
object Operator extends OperatorCommon

private[mada] trait OperatorCommon {
    type +[a <: Operatable_+, b <: a#Operand_+] = a# +[b]
    type -[a <: Operatable_-, b <: a#Operand_-] = a# -[b]
    type **[a <: Operatable_**, b <: a#Operand_**] = a# **[b]

    type ===[a <: Operatable_===, b <: a#Operand_===] = a# ===[b]
    type !==[a <: Operatable_===, b <: a#Operand_===] = a# !==[b]

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

    final  def !==[that <: Operand_===](that: that): !==[that] = ===(that).not
    final type !==[that <: Operand_===] = ===[that]#not
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

trait Operatable_** {
    type Operand_**
     def **[that <: Operand_**](that: that): **[that]
    type **[that <: Operand_**]
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
