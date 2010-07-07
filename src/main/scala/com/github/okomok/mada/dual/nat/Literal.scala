

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


/**
 * Contains natural number literals.
 */
object Literal extends LiteralCommon

private[mada] trait LiteralCommon {
    private[mada]  val _0B = Boolean._false
    private[mada]  val _1B = Boolean._true
    private[mada] type _0B = `false`
    private[mada] type _1B = `true`

	val  _0N = Nat._Nil
	val  _1N = NatCons(_1B, Nat._Nil)
	val  _2N = NatCons(_0B, NatCons(_1B, Nat._Nil))
	val  _3N = NatCons(_1B, NatCons(_1B, Nat._Nil))
	val  _4N = NatCons(_0B, NatCons(_0B, NatCons(_1B, Nat._Nil)))
	val  _5N = NatCons(_1B, NatCons(_0B, NatCons(_1B, Nat._Nil)))
	val  _6N = NatCons(_0B, NatCons(_1B, NatCons(_1B, Nat._Nil)))
	val  _7N = NatCons(_1B, NatCons(_1B, NatCons(_1B, Nat._Nil)))
	val  _8N = NatCons(_0B, NatCons(_0B, NatCons(_0B, NatCons(_1B, Nat._Nil))))
	val  _9N = NatCons(_1B, NatCons(_0B, NatCons(_0B, NatCons(_1B, Nat._Nil))))
	val _10N = NatCons(_0B, NatCons(_1B, NatCons(_0B, NatCons(_1B, Nat._Nil))))
	val _11N = NatCons(_1B, NatCons(_1B, NatCons(_0B, NatCons(_1B, Nat._Nil))))
	val _12N = NatCons(_0B, NatCons(_0B, NatCons(_1B, NatCons(_1B, Nat._Nil))))
	val _13N = NatCons(_1B, NatCons(_0B, NatCons(_1B, NatCons(_1B, Nat._Nil))))
	val _14N = NatCons(_0B, NatCons(_1B, NatCons(_1B, NatCons(_1B, Nat._Nil))))
	val _15N = NatCons(_1B, NatCons(_1B, NatCons(_1B, NatCons(_1B, Nat._Nil))))

	type  _0N = NatNil
	type  _1N = NatCons[_1B, NatNil]
	type  _2N = NatCons[_0B, NatCons[_1B, NatNil]]
	type  _3N = NatCons[_1B, NatCons[_1B, NatNil]]
	type  _4N = NatCons[_0B, NatCons[_0B, NatCons[_1B, NatNil]]]
	type  _5N = NatCons[_1B, NatCons[_0B, NatCons[_1B, NatNil]]]
	type  _6N = NatCons[_0B, NatCons[_1B, NatCons[_1B, NatNil]]]
	type  _7N = NatCons[_1B, NatCons[_1B, NatCons[_1B, NatNil]]]
	type  _8N = NatCons[_0B, NatCons[_0B, NatCons[_0B, NatCons[_1B, NatNil]]]]
	type  _9N = NatCons[_1B, NatCons[_0B, NatCons[_0B, NatCons[_1B, NatNil]]]]
	type _10N = NatCons[_0B, NatCons[_1B, NatCons[_0B, NatCons[_1B, NatNil]]]]
	type _11N = NatCons[_1B, NatCons[_1B, NatCons[_0B, NatCons[_1B, NatNil]]]]
	type _12N = NatCons[_0B, NatCons[_0B, NatCons[_1B, NatCons[_1B, NatNil]]]]
	type _13N = NatCons[_1B, NatCons[_0B, NatCons[_1B, NatCons[_1B, NatNil]]]]
	type _14N = NatCons[_0B, NatCons[_1B, NatCons[_1B, NatCons[_1B, NatNil]]]]
	type _15N = NatCons[_1B, NatCons[_1B, NatCons[_1B, NatCons[_1B, NatNil]]]]
}
