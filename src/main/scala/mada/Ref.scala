
package mada


case class Ref[T](var deref: T) {
    final def :=(x: T): Ref[T] = { deref = x; this }
}
