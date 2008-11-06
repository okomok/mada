
package mada


object UncheckedInstanceOf {
    def apply[T](that: Any): Option[T] = that match {
        case x: AnyVal => None
        case x: AnyRef if x eq null => None
        case _ => Some(that.asInstanceOf[T])
    }
}
