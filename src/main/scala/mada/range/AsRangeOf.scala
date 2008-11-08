
package mada.range


class AsRangeOf[To] {
    def apply[From](r: Range[From]): Range[To] = r.map((_: From).asInstanceOf[To])
}
