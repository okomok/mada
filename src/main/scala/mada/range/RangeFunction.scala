
package mada.range


trait RangeFunction[Y] {
    def fromRange[X]: Range[X] => Y
}

trait RangeTransformation {
    def fromRange[X]: Range[X] => Range[X]
}
