
package mada


object Autoboxing {
    // Byte
    implicit def madaAutoboxingToByte(from: java.lang.Byte): Byte = from.byteValue
    implicit def madaAutoboxingFromByte(from: Byte): java.lang.Byte = new java.lang.Byte(from)

    // Short
    implicit def madaAutoboxingToShort(from: java.lang.Short): Short = from.shortValue
    implicit def madaAutoboxingFromShort(from: Short): java.lang.Short = new java.lang.Short(from)

    // Int
    implicit def madaAutoboxingToInteger(from: java.lang.Integer): Int = from.intValue
    implicit def madaAutoboxingFromInteger(from: Int): java.lang.Integer = new java.lang.Integer(from)

    // Long
    implicit def madaAutoboxingToLong(from: java.lang.Long): Long = from.longValue
    implicit def madaAutoboxingFromLong(from: Long): java.lang.Long = new java.lang.Long(from)

    // Float
    implicit def madaAutoboxingToFloat(from: java.lang.Float): Float = from.floatValue
    implicit def madaAutoboxingFromFloat(from: Float): java.lang.Float = new java.lang.Float(from)

    // Double
    implicit def madaAutoboxingToDouble(from: java.lang.Double): Double = from.doubleValue
    implicit def madaAutoboxingFromDouble(from: Double): java.lang.Double = new java.lang.Double(from)

    // Boolean
    implicit def madaAutoboxingToBoolean(from: java.lang.Boolean): Boolean = from.booleanValue
    implicit def madaAutoboxingFromBoolean(from: Boolean): java.lang.Boolean = new java.lang.Boolean(from)

    // Char
    implicit def madaAutoboxingToChar(from: java.lang.Character): Char = from.charValue
    implicit def madaAutoboxingFromChar(from: Char): java.lang.Character = new java.lang.Character(from)
}
