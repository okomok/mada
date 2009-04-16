

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains utility methods parsing on char Vector.
 */
object Lexical extends LexicalConversions with LexicalCompatibles {
    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from(to: Vector[Char]): Vector[Char] = to

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: LexicalCompatibles = this
}


/**
 * Contains explicit conversions around char vector.
 */
trait LexicalConversions {
    import Vector.stringize

// from
    def fromAny(from: Any): Vector[Char] = Vector.unstringize(from.toString)

// to
    def toBoolean(from: Vector[Char]): Boolean = stringize(from).toBoolean
    def toByte(from: Vector[Char]): Byte = java.lang.Byte.parseByte(stringize(from))
    def toByte(from: Vector[Char], radix: Byte): Byte = java.lang.Byte.parseByte(stringize(from), radix)
    def toShort(from: Vector[Char]): Short = java.lang.Short.parseShort(stringize(from))
    def toShort(from: Vector[Char], radix: Short): Short = java.lang.Short.parseShort(stringize(from), radix)
    def toInt(from: Vector[Char]): Int = java.lang.Integer.parseInt(stringize(from))
    def toInt(from: Vector[Char], radix: Int): Int = java.lang.Integer.parseInt(stringize(from), radix)
    def toLong(from: Vector[Char]): Long = java.lang.Long.parseLong(stringize(from))
    def toLong(from: Vector[Char], radix: Int): Long = java.lang.Long.parseLong(stringize(from), radix)
    def toFloat(from: Vector[Char]): Float = java.lang.Float.parseFloat(stringize(from))
    def toDouble(from: Vector[Char]): Double = java.lang.Double.parseDouble(stringize(from))
}


/**
 * Contains implicit conversions around char vector.
 */
trait LexicalCompatibles {
    import Lexical._

// from
    implicit def madaCharVectorFromBoolean(from: Boolean): Vector[Char] = fromAny(from)
    implicit def madaCharVectorFromByte(from: Byte): Vector[Char] = fromAny(from)
    implicit def madaCharVectorFromShort(from: Short): Vector[Char] = fromAny(from)
    implicit def madaCharVectorFromInt(from: Int): Vector[Char] = fromAny(from)
    implicit def madaCharVectorFromLong(from: Long): Vector[Char] = fromAny(from)
    implicit def madaCharVectorFromFloat(from: Float): Vector[Char] = fromAny(from)
    implicit def madaCharVectorFromDouble(from: Double): Vector[Char] = fromAny(from)

// to
    implicit def madaCharVectorToBoolean(from: Vector[Char]): Boolean = toBoolean(from)
    implicit def madaCharVectorToByte(from: Vector[Char]): Byte = toByte(from)
    implicit def madaCharVectorToShort(from: Vector[Char]): Short = toShort(from)
    implicit def madaCharVectorToInt(from: Vector[Char]): Int = toInt(from)
    implicit def madaCharVectorToLong(from: Vector[Char]): Long = toLong(from)
    implicit def madaCharVectorToFloat(from: Vector[Char]): Float = toFloat(from)
    implicit def madaCharVectorToDouble(from: Vector[Char]): Double = toDouble(from)
}
