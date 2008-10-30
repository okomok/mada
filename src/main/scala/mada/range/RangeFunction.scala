
package mada.range


trait RangeFunction[Y] {
    def fromRange[X]: Range[X] => Y
}
