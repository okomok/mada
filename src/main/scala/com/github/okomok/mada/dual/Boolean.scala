

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Boolean
 */
sealed trait Boolean extends Any {
     def self: self
    type self <: Boolean

     def not: not
    type not <: Boolean

     def ===[that <: Boolean](that: that): ===[that]
    type ===[that <: Boolean] <: Boolean

    final  def !==[that <: Boolean](that: that): !==[that] = ===(that).not
    final type !==[that <: Boolean] = ===[that]#not

     def &&[that <: Boolean](that: that): &&[that]
    type &&[that <: Boolean] <: Boolean

     def ||[that <: Boolean](that: that): ||[that]
    type ||[that <: Boolean] <: Boolean

    private[mada]  def isTrue: isTrue
    private[mada] type isTrue <: Boolean

    private[mada]  def isFalse: isFalse
    private[mada] type isFalse <: Boolean

     def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else]
    type `if`[then <: Function0, _else <: Function0] <: Function0

    final override  def asInstanceOfBoolean = self
    final override type asInstanceOfBoolean = self

    def undual: undual
    final type undual = scala.Boolean

    final override def equals(that: scala.Any) = that match {
        case that: Boolean => undual == that.undual
        case _ => false
    }
}

/**
 * The dual true
 */
sealed trait `true` extends Boolean {
    override  def self = this
    override type self = `true`

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

    override  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = then
    override type `if`[then <: Function0, _else <: Function0] = then

    override def undual = true
}

/**
 * The dual false
 */
sealed trait `false` extends Boolean {
    override  def self = this
    override type self = `false`

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

    override  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = _else
    override type `if`[then <: Function0, _else <: Function0] = _else

    override def undual = false
}


object Boolean {
    private[mada] val _true = new `true`{}
    private[mada] val _false = new `false`{}

    // implicit def _undual[c <: Boolean](c: c): scala.Boolean = c.undual
}
