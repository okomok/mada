
package mada


object Autoboxing {
    // Byte
    implicit def madaAutoboxingToByte(from: java.lang.Byte) = from.byteValue
    implicit def madaAutoboxingFromByte(from: Byte) = new java.lang.Byte(from)

    // Short
    implicit def madaAutoboxingToShort(from: java.lang.Short) = from.shortValue
    implicit def madaAutoboxingFromShort(from: Short) = new java.lang.Short(from)

    // Int
    implicit def madaAutoboxingToInteger(from: java.lang.Integer) = from.intValue
    implicit def madaAutoboxingFromInteger(from: Int) = new java.lang.Integer(from)

    // Long
    implicit def madaAutoboxingToLong(from: java.lang.Long) = from.longValue
    implicit def madaAutoboxingFromLong(from: Long) = new java.lang.Long(from)

    // Float
    implicit def madaAutoboxingToFloat(from: java.lang.Float) = from.floatValue
    implicit def madaAutoboxingFromFloat(from: Float) = new java.lang.Float(from)

    // Double
    implicit def madaAutoboxingToDouble(from: java.lang.Double) = from.doubleValue
    implicit def madaAutoboxingFromDouble(from: Double) = new java.lang.Double(from)

    // Boolean
    implicit def madaAutoboxingToBoolean(from: java.lang.Boolean) = from.booleanValue
    implicit def madaAutoboxingFromBoolean(from: Boolean) = new java.lang.Boolean(from)

    // Char
    implicit def madaAutoboxingToChar(from: java.lang.Character) = from.charValue
    implicit def madaAutoboxingFromChar(from: Char) = new java.lang.Character(from)
}
