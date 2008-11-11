
package mada.rng

import Map._


class AsRngOf[To] {
    def apply[From](r: Rng[From]): Rng[To] = r.map((_: From).asInstanceOf[To])
}
