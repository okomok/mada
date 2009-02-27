

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains explicit conversions around char vector.
 */
trait ParseConversions {
    import Vector.stringize

    def toBoolean(v: Vector[Char]): Boolean = stringize(v).toBoolean
    def toByte(v: Vector[Char]): Byte = java.lang.Byte.parseByte(stringize(v))
    def toByte(v: Vector[Char], radix: Byte): Byte = java.lang.Byte.parseByte(stringize(v), radix)
    def toShort(v: Vector[Char]): Short = java.lang.Short.parseShort(stringize(v))
    def toShort(v: Vector[Char], radix: Short): Short = java.lang.Short.parseShort(stringize(v), radix)
    def toInt(v: Vector[Char]): Int = java.lang.Integer.parseInt(stringize(v))
    def toInt(v: Vector[Char], radix: Int): Int = java.lang.Integer.parseInt(stringize(v), radix)
    def toLong(v: Vector[Char]): Long = java.lang.Long.parseLong(stringize(v))
    def toLong(v: Vector[Char], radix: Int): Long = java.lang.Long.parseLong(stringize(v), radix)
    def toFloat(v: Vector[Char]): Float = java.lang.Float.parseFloat(stringize(v))
    def toDouble(v: Vector[Char]): Double = java.lang.Double.parseDouble(stringize(v))
}

/**
 * Contains implicit conversions around char vector.
 */
trait ParseCompatibles {
    import Vector.stringize

    implicit def madaCharVectortoBoolean(v: Vector[Char]): Boolean = stringize(v).toBoolean
    implicit def madaCharVectortoByte(v: Vector[Char]): Byte = java.lang.Byte.parseByte(stringize(v))
    implicit def madaCharVectortoShort(v: Vector[Char]): Short = java.lang.Short.parseShort(stringize(v))
    implicit def madaCharVectortoInt(v: Vector[Char]): Int = java.lang.Integer.parseInt(stringize(v))
    implicit def madaCharVectortoLong(v: Vector[Char]): Long = java.lang.Long.parseLong(stringize(v))
    implicit def madaCharVectortoFloat(v: Vector[Char]): Float = java.lang.Float.parseFloat(stringize(v))
    implicit def madaCharVectortoDouble(v: Vector[Char]): Double = java.lang.Double.parseDouble(stringize(v))
}

/**
 * Contains utility methods parsing on char Vector.
 */
object Parse extends ParseConversions with ParseCompatibles {
    /**
     * @return  <code>this</code>.
     */
    val Compatibles: ParseCompatibles = this
}
