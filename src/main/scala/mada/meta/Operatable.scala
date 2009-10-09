

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


// See: Utils.scala
//      at http://www.assembla.com/wiki/show/metascala


trait Operatable extends Operatable_==
    with Operatable_+ with Operatable_-
    with Operatable_&& with Operatable_||

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

trait Operatable_&& {
    type Operand_&&
    type operator_&&[that <: Operand_&&]
}

trait Operatable_|| {
    type Operand_||
    type operator_||[that <: Operand_||]
}
