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
package uk.ac.leeds.ccg.v3d.geometry.envelope;

import uk.ac.leeds.ccg.v3d.geometry.V3D_Point;

/**
 * For the bottom-fore edge of an envelope. For the two points that define the
 * line segment, {@link #p} is the bottom-right-fore point (brf) and {@link q}
 * is the bottom-left-fore point (blf). {@code blf.x} should be less than or
 * equal to {@code brf.x}: {@code blf.y} should equal {@code blf.y}:
 * {@code blf.z} should equal {@code blf.z}. No checking of these conditions is
 * done for efficiency reasons.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class V3D_EnvelopeEdgeBottomFore extends V3D_EnvelopeEdge {

    /**
     * @param brf The point in the bottom-right-fore of an envelope.
     * @param blf The point in the bottom-left-fore of an envelope.
     */
    public V3D_EnvelopeEdgeBottomFore(V3D_Point brf, V3D_Point blf) {
        super(brf, blf);
    }

    public V3D_Point getBrf() {
        return p;
    }

    public V3D_Point getBlf() {
        return q;
    }

}
