

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Boolean
 */
sealed trait Boolean {
     def not: not
    type not <: Boolean

     def ===[that <: Boolean](that: that): ===[that]
    type ===[that <: Boolean] <: Boolean

     def &&[that <: Boolean](that: that): &&[that]
    type &&[that <: Boolean] <: Boolean

     def ||[that <: Boolean](that: that): ||[that]
    type ||[that <: Boolean] <: Boolean

    final  def !==[that <: Boolean](that: that): !==[that] = ===(that).not
    final type !==[that <: Boolean] = ===[that]#not

    private[mada]  def isTrue: isTrue
    private[mada] type isTrue <: Boolean

    private[mada]  def isFalse: isFalse
    private[mada] type isFalse <: Boolean

    private[mada]  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else]
    private[mada] type `if`[then <: Function0, _else <: Function0] <: Function0

    private[mada]  def if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean](then: then, _else: _else): if_Boolean[then, _else]
    private[mada] type if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean] <: Function0_Boolean

    private[mada]  def if_Nat[then <: Function0_Nat, _else <: Function0_Nat](then: then, _else: _else): if_Nat[then, _else]
    private[mada] type if_Nat[then <: Function0_Nat, _else <: Function0_Nat] <: Function0_Nat

    private[mada]  def if_List[then <: Function0_List, _else <: Function0_List](then: then, _else: _else): if_List[then, _else]
    private[mada] type if_List[then <: Function0_List, _else <: Function0_List] <: Function0_List

    def undual: undual
    final type undual = scala.Boolean
}

/**
 * The dual true
 */
sealed trait `true` extends Boolean {
    override  def not: not = `false`
    override type not = `false`

    override  def ===[that <: Boolean](that: that): ===[that] = that.isTrue
    override type ===[that <: Boolean] = that#isTrue

    override  def &&[that <: Boolean](that: that): &&[that] = that
    override type &&[that <: Boolean] = that

    override  def ||[that <: Boolean](that: that): ||[that] = `true`
    override type ||[that <: Boolean] = `true`

    override private[mada]  def isTrue: isTrue = `true`
    override private[mada] type isTrue = `true`

    override private[mada]  def isFalse: isFalse = `false`
    override private[mada] type isFalse = `false`

    override private[mada]  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = then
    override private[mada] type `if`[then <: Function0, _else <: Function0] = then

    override private[mada]  def if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean](then: then, _else: _else): if_Boolean[then, _else] = then
    override private[mada] type if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean] = then

    override private[mada]  def if_Nat[then <: Function0_Nat, _else <: Function0_Nat](then: then, _else: _else): if_Nat[then, _else] = then
    override private[mada] type if_Nat[then <: Function0_Nat, _else <: Function0_Nat] = then

    override private[mada]  def if_List[then <: Function0_List, _else <: Function0_List](then: then, _else: _else): if_List[then, _else] = then
    override private[mada] type if_List[then <: Function0_List, _else <: Function0_List] = then

    override def undual = true
}

/**
 * The dual false
 */
sealed trait `false` extends Boolean {
    override  def not: not = `true`
    override type not = `true`

    override  def ===[that <: Boolean](that: that): ===[that] = that.isFalse
    override type ===[that <: Boolean] = that#isFalse

    override  def &&[that <: Boolean](that: that): &&[that] = `false`
    override type &&[that <: Boolean] = `false`

    override  def ||[that <: Boolean](that: that): ||[that] = that
    override type ||[that <: Boolean] = that

    override private[mada]  def isTrue: isTrue = `false`
    override private[mada] type isTrue = `false`

    override private[mada]  def isFalse: isFalse = `true`
    override private[mada] type isFalse = `true`

    override private[mada]  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = _else
    override private[mada] type `if`[then <: Function0, _else <: Function0] = _else

    override private[mada]  def if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean](then: then, _else: _else): if_Boolean[then, _else] = _else
    override private[mada] type if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean] = _else

    override private[mada]  def if_Nat[then <: Function0_Nat, _else <: Function0_Nat](then: then, _else: _else): if_Nat[then, _else] = _else
    override private[mada] type if_Nat[then <: Function0_Nat, _else <: Function0_Nat] = _else

    override private[mada]  def if_List[then <: Function0_List, _else <: Function0_List](then: then, _else: _else): if_List[then, _else] = _else
    override private[mada] type if_List[then <: Function0_List, _else <: Function0_List] = _else

    override def undual = false
}


object Boolean {
    private[mada] val _true = new `true`{}
    private[mada] val _false = new `false`{}

    // implicit def _undual[c <: Boolean](c: c): scala.Boolean = c.undual
}
