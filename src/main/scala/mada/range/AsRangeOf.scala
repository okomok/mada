
package mada.range


class AsRangeOf[To] {
    def apply[From](r: Range[From]): Range[To] = r.transform((_: From).asInstanceOf[To])
}
