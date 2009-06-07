

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


/**
 * Provides lexical conversions.
 */
case class Lexical(_1: Iterative[Char]) {
    def toBoolean: Boolean = _1.stringize.toBoolean
    def toByte: Byte = java.lang.Byte.parseByte(_1.stringize)
    def toByte(radix: Byte): Byte = java.lang.Byte.parseByte(_1.stringize, radix)
    def toShort: Short = java.lang.Short.parseShort(_1.stringize)
    def toShort(radix: Short): Short = java.lang.Short.parseShort(_1.stringize, radix)
    def toInt: Int = java.lang.Integer.parseInt(_1.stringize)
    def toInt(radix: Int): Int = java.lang.Integer.parseInt(_1.stringize, radix)
    def toLong: Long = java.lang.Long.parseLong(_1.stringize)
    def toLong(radix: Int): Long = java.lang.Long.parseLong(_1.stringize, radix)
    def toFloat: Float = java.lang.Float.parseFloat(_1.stringize)
    def toDouble: Double = java.lang.Double.parseDouble(_1.stringize)
}
