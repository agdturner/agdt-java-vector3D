/*
 * Copyright 2020 Andy Turner, University of Leeds.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.v3d.geometry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import uk.ac.leeds.ccg.math.Math_BigDecimal;
import uk.ac.leeds.ccg.v3d.core.V3D_Environment;

/**
 * V3D_Point
 *
 * @author Andy Turner
 * @version 1.0
 */
public class V3D_Point extends V3D_Geometry
        implements V3D_FiniteGeometry {

    /**
     * The x coordinate.
     */
    public BigDecimal x;

    /**
     * The y coordinate.
     */
    public BigDecimal y;

    /**
     * The z coordinate.
     */
    public BigDecimal z;

    /**
     * @param p The point to duplicate
     */
    public V3D_Point(V3D_Point p) {
        super(p.e);
        x = p.x;
        y = p.y;
        z = p.z;
    }

    /**
     * @param e What {@link #e} is set to.
     * @param x What {@link #x} is set to.
     * @param y What {@link #y} is set to.
     * @param z What {@link #z} is set to.
     */
    public V3D_Point(V3D_Environment e, BigDecimal x, BigDecimal y,
            BigDecimal z) {
        super(e);
        this.x = new BigDecimal(x.toString());
        this.y = new BigDecimal(y.toString());
        this.z = new BigDecimal(z.toString());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(x=" + x.toString()
                + ", y=" + y.toString() + ", z=" + z.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof V3D_Point) {
            V3D_Point p = (V3D_Point) o;
            if (p.x.compareTo(x) == 0) {
                if (p.y.compareTo(y) == 0) {
                    if (p.z.compareTo(z) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.x);
        hash = 43 * hash + Objects.hashCode(this.y);
        hash = 43 * hash + Objects.hashCode(this.z);
        return hash;
    }

    /**
     * @param v The vector to apply.
     * @return a new point which is this shifted by v.
     */
    public V3D_Point apply(V3D_Vector v) {
        return new V3D_Point(e, x.add(v.dx), y.add(v.dy), z.add(v.dz));
    }

    /**
     * Get the distance between this and {@code p}.
     *
     * @param p A point.
     * @param scale The scale. A positive value gives the number of decimal
     * places. A negative value rounds to the left of the decimal point.
     * @param rm The RoundingMode for the calculation.
     * @return The distance from {@code p} to this.
     */
    public BigDecimal getDistance(V3D_Point p, int scale, RoundingMode rm) {
        if (this.equals(p)) {
            return BigDecimal.ZERO;
        }
        BigDecimal dx = this.x.subtract(p.x);
        BigDecimal dy = this.y.subtract(p.y);
        BigDecimal dz = this.z.subtract(p.z);
        return Math_BigDecimal.sqrt(dx.pow(2).add(dy.pow(2)).add(dz.pow(2)),
                scale, rm);
    }

    /**
     * Get the distance between this and {@code l}.
     *
     * @param l A line.
     * @param scale The scale. A positive value gives the number of decimal
     * places. A negative value rounds to the left of the decimal point.
     * @param rm The RoundingMode for the calculation.
     * @return The distance from {@code p} to this.
     */
    public BigDecimal getDistance(V3D_Line l, int scale, RoundingMode rm) {
        if (l.isIntersectedBy(this)) {
            return BigDecimal.ZERO;
        }
        l.p.getDistance(l.q, scale, rm);
        V3D_Vector pqu = l.pq.getUnitVector(scale, rm);
        V3D_Vector v = new V3D_Vector(this, l.p);
        BigDecimal v_dot_pqu = v.getDotProduct(pqu);
        V3D_Point p = l.q.apply(pqu.multiply(v_dot_pqu));
        return p.getDistance(this, scale, rm);
    }

    @Override
    public V3D_Envelope getEnvelope3D() {
        return new V3D_Envelope(e, x, y, z);
    }

}
