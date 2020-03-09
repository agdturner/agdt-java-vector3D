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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.generic.io.Generic_Defaults;
import uk.ac.leeds.ccg.math.Math_BigDecimal;
import uk.ac.leeds.ccg.v3d.core.V3D_Environment;

/**
 * V3D_PlaneTest
 *
 * @author Andy Turner
 * @version 1.0
 */
public class V3D_PlaneTest extends V3D_Test {

    public V3D_PlaneTest() throws Exception {
        super(new V3D_Environment(new Generic_Environment(
                new Generic_Defaults())));
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

//    @Test
//    public void run() {
//        testToString();
//        testIsOnPlane_V3D_Point();
//        ...
//    }
    /**
     * Test of toString method, of class V3D_Plane.
     */
    //@Test
    public void testToString() {
        System.out.println("toString");
        V3D_Plane instance = getPlane(P0P0P0, P1P1P1, P1P0P0);
        String expResult = "V3D_Plane(p=V3D_Point(x=0, y=0, z=0), "
                + "q=V3D_Point(x=1, y=1, z=1), r=V3D_Point(x=1, y=0, z=0))";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Basic method to get a plane defined by {@code p}, {@code q} and
     * {@code r}.
     *
     * @param p A point.
     * @param q A point.
     * @param r A point.
     * @return A plane or null if the points {@code p}, {@code q} and {@code r}
     * are collinear or coincident.
     */
    public V3D_Plane getPlane(V3D_Point p, V3D_Point q, V3D_Point r) {
        try {
            return new V3D_Plane(e, p, q, r);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * Test of isOnPlane method, of class V3D_Plane.
     */
    @Test
    public void testIsOnPlane_V3D_Point() {
        System.out.println("isOnPlane");
        V3D_Point pt = P1P0P0;
        V3D_Plane instance = getPlane(P1P1P0, P1P1P1, P1P0P0);
        boolean expResult = true;
        boolean result = instance.isIntersectedBy(pt);
        assertEquals(expResult, result);
        // Test2
        pt = P1P0P1;
        instance = getPlane(P1P1P0, P1P1P1, P1P0P0);
        expResult = true;
        result = instance.isIntersectedBy(pt);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOnPlane method, of class V3D_Plane.
     */
    @Test
    public void testIsOnPlane_V3D_LineSegment() {
        System.out.println("isOnPlane");
        V3D_Point end = new V3D_Point(e, P0, P0, P2);
        V3D_LineSegment l = new V3D_LineSegment(P1P0P1, end);
        V3D_Plane instance = getPlane(P1P1P0, P1P1P1, P1P0P0);
        boolean expResult = false;
        boolean result = instance.isOnPlane(l);
        assertEquals(expResult, result);
        // Test 2
        end = new V3D_Point(e, P1, P0, P2);
        l = new V3D_LineSegment(P1P0P1, end);
        instance = getPlane(P1P1P0, P1P1P1, P1P0P0);
        expResult = true;
        result = instance.isOnPlane(l);
        assertEquals(expResult, result);
        // Test 3
        end = new V3D_Point(e, P1, P0, P2);
        l = new V3D_LineSegment(P1P0P1, end);
        instance = getPlane(P1P1P0, P1P1P1, P1P0P0);
        expResult = true;
        result = instance.isOnPlane(l);
        assertEquals(expResult, result);
        // Test 4
        end = new V3D_Point(e, P1, P10, P10);
        l = new V3D_LineSegment(P1N1N1, end);
        instance = getPlane(P1P1P0, P1P1P1, P1P0P0);
        expResult = true;
        result = instance.isOnPlane(l);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class V3D_Plane.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = getPlane(P0P1P0, P1P1P1, P1P0P0);
        V3D_Plane instance = getPlane(P0P1P0, P1P1P1, P1P0P0);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // Test 2
        instance = getPlane(P1P1P0, P1P1P1, P1P0P0);
        expResult = false;
        result = instance.equals(o);
        assertEquals(expResult, result);
        // Test 3
        instance = getPlane(P1P1P1, P1P0P0, P0P1P0);
        expResult = true;
        result = instance.equals(o);
        assertEquals(expResult, result);
        // Test 4
        instance = getPlane(P1P0P0, P0P1P0, P1P1P1);
        expResult = true;
        result = instance.equals(o);
        assertEquals(expResult, result);
        // Test 4
        instance = getPlane(P1P0P0, P0P1P0, P1P1P1);
        expResult = true;
        result = instance.equals(o);
        assertEquals(expResult, result);
        // Test 5
        o = getPlane(P0P0P0, P1P0P0, P0P1P0);
        V3D_Point q = new V3D_Point(e, P2, P0, P0);
        V3D_Point r = new V3D_Point(e, P0, P2, P0);
        instance = getPlane(P0P0P0, q, r);
        expResult = true;
        result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of isIntersectedBy method, of class V3D_Plane.
     */
    @Test
    public void testIsIntersectedBy_3args() {
        System.out.println("intersects");
        V3D_Plane pl = getPlane(P0P0P0, P1P0P0, N1P0P1);
        int scale = 1;
        RoundingMode rm = RoundingMode.HALF_UP;
        V3D_Plane instance = getPlane(P0P0P0, P1P0P0, N1P0P1);
        boolean expResult = true;
        boolean result = instance.isIntersectedBy(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 2
        scale = 1;
        rm = RoundingMode.HALF_UP;
        instance = getPlane(N1N1N1, P0N1N1, new V3D_Point(e,
                BigDecimal.valueOf(-2), N1, P0));
        expResult = false;
        result = instance.isIntersectedBy(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 3
        scale = 0;
        rm = RoundingMode.HALF_UP;
        pl = getPlane(P0P0P0, P1P0P0, N1P0P1);
        instance = getPlane(N1N1N1, P0N1N1, new V3D_Point(e,
                BigDecimal.valueOf(-2), N1, P0));
        System.out.println("pl.getNPerp()=" + pl.getNormalVector());
        System.out.println("instance.getNPerp()=" + instance.getNormalVector());
        expResult = false;
        result = instance.isIntersectedBy(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 4
        scale = 2;
        rm = RoundingMode.HALF_UP;
        expResult = false;
        result = instance.isIntersectedBy(pl, scale, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of isIntersectedBy method, of class V3D_Plane.
     */
    @Test
    public void testIsIntersectedBy_V3D_Point() {
        System.out.println("intersects");
        V3D_Point pt = P0P0P0;
        V3D_Plane instance = getPlane(P0P0P0, P1P0P0, N1P0P1);
        boolean expResult = true;
        boolean result = instance.isIntersectedBy(pt);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOnPlane method, of class V3D_Plane.
     */
    @Test
    public void testIsOnPlane() {
        System.out.println("isOnPlane");
        V3D_LineSegment l = new V3D_LineSegment(P0P0P0, P1P0P0);
        V3D_Plane instance = getPlane(P0P0P0, P1P0P0, N1P0P1);
        boolean expResult = true;
        boolean result = instance.isOnPlane(l);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class V3D_Plane.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        assertTrue(true); // Not really a test - method does not need testing.
    }

    /**
     * Test of getNormalVector method, of class V3D_Plane.
     */
    @Test
    public void testGetNormalVector() {
        System.out.println("getNormalVector");
        V3D_Plane instance = getPlane(P0P0P0, P1P0P0, P0P1P0); // Z = 0
        V3D_Vector expResult = new V3D_Vector(P0P0P1);
        V3D_Vector result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 2
        instance = getPlane(P0P0N1, P1P0N1, P0P1N1); // Z = -1
        expResult = new V3D_Vector(P0P0P1);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 3
        instance = getPlane(P0P0P1, P1P0P1, P0P1P1); // Z = 1
        expResult = new V3D_Vector(P0P0P1);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 4
        instance = getPlane(P1P0P1, P0P1P1, P0P0P1); // Z = 1
        expResult = new V3D_Vector(P0P0P1);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 5
        instance = getPlane(P0P1P1, P0P0P1, P1P0P1); // Z = 1
        expResult = new V3D_Vector(P0P0P1);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 6
        instance = getPlane(P0P0P0, P0P1P0, P0P0N1); // Y = 0
        expResult = new V3D_Vector(N1P0P0);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 7
        instance = getPlane(P0P0P0, P1P0P0, P0P0N1); // X = 0
        expResult = new V3D_Vector(P0P1P0);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 8
        instance = getPlane(P0P0P0, P1P0P0, N1P0P1); // X = 0
        expResult = new V3D_Vector(P0N1P0); // This is the other normal than for
        result = instance.getNormalVector();// test 7 due to the right hand rule  
        assertEquals(expResult, result);    // and the orientation.
        instance = getPlane(P0P1P0, P1P1P1, P1P0P0);
        expResult = new V3D_Vector(P1P1N1);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 2
        instance = getPlane(P0P0P0, P0P1P1, P0N1P0);
        expResult = new V3D_Vector(P1P0P0);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
        // Test 3
        instance = getPlane(P0P0P0, P1P1P1, P0N1N1);
        expResult = new V3D_Vector(P0P1N1);
        result = instance.getNormalVector();
        assertEquals(expResult, result);
    }

    /**
     * Test of isIntersectedBy method, of class V3D_Plane.
     */
    @Test
    public void testIsIntersectedBy_3args_1() {
        System.out.println("intersects");
        V3D_Plane pl = getPlane(P1P0P0, P0P1P0, N1P0P0); // Z = 0 plane
        int scale = 1;
        RoundingMode rm = RoundingMode.HALF_UP;
        V3D_Plane instance = getPlane(P1P0P1, P0P0P1, N1P0N1); // Y = 0 plane
        boolean expResult = true;
        boolean result = instance.isIntersectedBy(pl, scale, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of isIntersectedBy method, of class V3D_Plane.
     */
    @Test
    public void testIsIntersectedBy_3args_2() {
        System.out.println("intersects");
        V3D_Line l = new V3D_Line(P0P0P0, P0P1P0); // X axis
        int scale = 1;
        RoundingMode rm = RoundingMode.HALF_UP;
        V3D_Plane instance = getPlane(P1P0P1, P0P0P1, N1P0N1); // Y = 0 plane
        boolean expResult = true;
        boolean result = instance.isIntersectedBy(l, scale, rm);
        assertEquals(expResult, result);
        // Test 2
        l = new V3D_Line(N1N1N1, P1P1P1);
        instance = new V3D_Plane(e, P0N1N1, P1N1N1, P0P0N1);
        expResult = true;
        result = instance.isIntersectedBy(l, scale, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of isParallel method, of class V3D_Plane.
     */
    @Test
    public void testIsParallel_3args_1() {
        System.out.println("isParallel");
        V3D_Plane p = getPlane(P1P1P0, P1N1P0, N1P1P0);
        int scale = 1;
        RoundingMode rm = RoundingMode.HALF_UP;
        V3D_Plane instance = getPlane(P1P1P1, P1N1P1, N1P1P1);
        boolean expResult = true;
        boolean result = instance.isParallel(p, scale, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of isParallel method, of class V3D_Plane.
     */
    @Test
    public void testIsParallel_3args_2() {
        System.out.println("isParallel");
        V3D_Line l = new V3D_Line(P1P1P0, P1N1P0);
        int scale = 1;
        RoundingMode rm = RoundingMode.HALF_UP;
        V3D_Plane instance = getPlane(P1P1P1, P1N1P1, N1P1P1);
        boolean expResult = true;
        boolean result = instance.isParallel(l, scale, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntersection method, of class V3D_Plane.
     */
    @Test
    public void testGetIntersection_3args_2() {
        System.out.println("getIntersection");
        V3D_Plane pl;
        int scale = 1;
        RoundingMode rm = RoundingMode.HALF_UP;
        V3D_Plane instance;
        V3D_Geometry expResult;
        V3D_Geometry result;
//        // Test 1 to 4 
        pl = getPlane(P0P0P0, e.yAxis.q, e.zAxis.q); // x=0
        // Test 1
        instance = getPlane(P0P0P0, e.yAxis.q, e.zAxis.q); // x=0
        expResult = getPlane(P0P0P0, e.yAxis.q, e.zAxis.q);
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 2
        instance = getPlane(e.xAxis.q, P0P0P0, e.zAxis.q); // y=0
        expResult = e.zAxis;                               // x=0, y=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 3
        instance = getPlane(e.xAxis.q, e.yAxis.q, P0P0P0); // z=0
        expResult = e.yAxis;                               // x=0. z=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 4
        instance = getPlane(N1P1N1, P0P1P0, P1P1N1); // y=1
        expResult = new V3D_Line(P0P1P0, P0P1P1);    // x=0, y=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 5
        instance = getPlane(P1P0P1, P0N1P1, P0P0P1); // z=1
        expResult = new V3D_Line(P0P1P1, P0P0P1);    // x=0, z=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 6 to 9
        pl = getPlane(e.xAxis.q, P0P0P0, e.zAxis.q); // y=0
        // Test 6
        instance = getPlane(P0P0P0, e.yAxis.q, e.zAxis.q); // x=0
        expResult = new V3D_Line(P0P0N1, P0P0P1);          // x=0, y=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 7
        instance = getPlane(e.xAxis.q, e.yAxis.q, P0P0P0); // z=0
        expResult = new V3D_Line(P0P0P0, P1P0P0);          // y=0, z=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 8
        instance = getPlane(P1P0P0, P1P1P0, P1P0P1);       // x=1
        expResult = new V3D_Line(P1P0N1, P1P0P1);          // x=1, y=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 9
        instance = getPlane(P0P1P1, P1P1P1, P0P0P1);       // z=1
        expResult = new V3D_Line(P0P0P1, P1P0P1);          // y=0, z=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 10 to 13
        pl = getPlane(e.xAxis.q, e.yAxis.q, P0P0P0); // z=0
        // Test 10
        instance = getPlane(P0P0P0, e.yAxis.q, e.zAxis.q); // x=0
        expResult = new V3D_Line(P0N1P0, P0P1P0);          // x=0, z=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 11
        instance = getPlane(e.xAxis.q, P0P0P0, e.zAxis.q); // y=0
        expResult = new V3D_Line(N1P0P0, P1P0P0);          // y=0, z=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 12
        instance = getPlane(P1P0P0, P1P1P1, P1P0P1); // x=1
        expResult = new V3D_Line(P1N1P0, P1P1P0);    // x=1, z=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 13
        instance = getPlane(P1P1P1, P0P1P0, P1P1P0); // y=1
        expResult = new V3D_Line(N1P1P0, P1P1P0);    // y=1, z=0
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 14 to 15
        pl = getPlane(P1P0P0, P1P1P1, P1P0P1); // x=1
        // Test 14
        instance = getPlane(N1P1N1, P0P1P0, P1P1N1); // y=1
        expResult = new V3D_Line(P1P1P0, P1P1P1);    // x=1, y=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 15
        instance = getPlane(P1P0P1, P0N1P1, P0P0P1); // z=1
        expResult = new V3D_Line(P1P1P1, P1P0P1);    // x=1, z=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 16 to 17
        pl = getPlane(N1P1N1, P0P1P0, P1P1N1); // y=1
        // Test 16
        instance = getPlane(P1P0P0, P1P1P1, P1P0P1); // x=1
        expResult = new V3D_Line(P1P1P0, P1P1P1);    // x=1, y=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 17
        instance = getPlane(P1P0P1, P0N1P1, P0P0P1); // z=1
        expResult = new V3D_Line(P1P1P1, P0P1P1);    // y=1, z=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 18 to 19
        pl = getPlane(P1P0P1, P0N1P1, P0P0P1); // z=1
        // Test 18
        instance = getPlane(P1P0P0, P1P1P1, P1P0P1); // x=1
        expResult = new V3D_Line(P1P0P1, P1P1P1);    // x=1, z=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        // Test 19
        instance = getPlane(N1P1N1, P0P1P0, P1P1N1); // y=1
        expResult = new V3D_Line(P1P1P1, P0P1P1);    // y=1, z=1
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);
        
        
        // Test z
        // x+2y+z−1=0       --- 1
        // 2x+5y−2z+2=0     --- 2
        // Choose one of A,B,C that is not 0. Without loss of generality, 
        // assume that's C. You can choose (a+1,b,c+μ) and (a,b+1,c+ν). To have 
        // them on the plane, just plug them into the equation:
        // A(a+1)+Bb+C(c+μ)+D=0
        // Aa+Bb+Cc+D+A+Cμ=0
        // μ=−A/C
        // ν=−B/C
        // From 1:
        // If x=0, y=0, then z=1 (0,0,1)
        // If y=0, z=0 then x=1 (1,0,0)
        // If x=0, z=0 then y=0.5 (0,0.5,0)
        // From 2:
        // If x=0, y=0, then z=1 (0,0,1)
        // If y=0, z=0 then x=-1 (-1,0,0)
        // If x=0, z=0 then y=-2/5 (0,-2/5,0)
        scale = 10;
        pl = getPlane(P0P0P1, P1P0P0, new V3D_Point(e, P0, BigDecimal.valueOf(0.5), P0));
        instance = getPlane(P0P0P1, N1P0P0, new V3D_Point(e, P0, Math_BigDecimal.divideRoundIfNecessary(N2, P5, scale, rm), P0));
        // x+2y+z−1=0       --- 1
        // 2x+5y−2z+2=0     --- 2
        // x=1-2y-t
        // y=4t-4
        // x==1-2(4t-4)-t
        // x==9-9t
        // (9,−4,0)+(-9,4,1)t.
        V3D_Point p = new V3D_Point(e, P9, N4, P0);
        V3D_Vector v = new V3D_Vector(e, N9, P4, P1);
        expResult = new V3D_Line(p, p.apply(v));
        result = instance.getIntersection(pl, scale, rm);
        if (result.equals(expResult)) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }

        // Test y
        // x+2y+z−1=0       --- 1
        // 2x+3y−2z+2=0     --- 2
        // Choose one of A,B,C that is not 0. Without loss of generality, 
        // assume that's C. You can choose (a+1,b,c+μ) and (a,b+1,c+ν). To have 
        // them on the plane, just plug them into the equation:
        // A(a+1)+Bb+C(c+μ)+D=0
        // Aa+Bb+Cc+D+A+Cμ=0
        // μ=−A/C
        // ν=−B/C
        // From 1:
        // If x=0, y=0, then z=1 (0,0,1)
        // If y=0, z=0 then x=1 (1,0,0)
        // If x=0, z=0 then y=0.5 (0,0.5,0)
        // From 2:
        // If x=0, y=0, then z=1 (0,0,1)
        // If y=0, z=0 then x=-1 (-1,0,0)
        // If x=0, z=0 then y=-2/3 (0,-2/3,0)
        scale = 10;
        pl = getPlane(P0P0P1, P1P0P0, new V3D_Point(e, P0, BigDecimal.valueOf(0.5), P0));
        instance = getPlane(P0P0P1, N1P0P0, new V3D_Point(e, P0, Math_BigDecimal.divideRoundIfNecessary(N2, P3, scale, rm), P0));
        // (−7,4,0)+(7,−4,1)t.
        p = new V3D_Point(e, N7, P4, P0);
        v = new V3D_Vector(e, P7, N4, P1);
        expResult = new V3D_Line(p, p.apply(v));
        result = instance.getIntersection(pl, scale, rm);
        if (result.equals(expResult)) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }

        // Test x
        V3D_Point P2P3P5 = new V3D_Point(e, P2, P3, P5);
        V3D_Point P7P11P13 = new V3D_Point(e, P7, BigDecimal.valueOf(11), BigDecimal.valueOf(13));
        pl = getPlane(P1P1P1, P2P3P5, P7P11P13);
        V3D_Point N7N11N13 = new V3D_Point(e, N7, BigDecimal.valueOf(-11), BigDecimal.valueOf(-13));
        V3D_Point N2N3N5 = new V3D_Point(e, N2, N3, N5);
        instance = getPlane(N2N3N5, P1P1P1, N7N11N13);
        expResult = e.yAxis;
        result = instance.getIntersection(pl, scale, rm);
        assertEquals(expResult, result);

    }

    /**
     * Test of getIntersection method, of class V3D_Plane.
     */
    @Test
    public void testGetIntersection_3args_1() {
        System.out.println("getIntersection");
//        V3D_Line l = new V3D_Line(new V3D_Point(N1N1N1), new V3D_Point(P1P1P1));
//        int scale = 1;
//        RoundingMode rm = RoundingMode.HALF_UP;
//        V3D_Plane instance = new V3D_Plane(e, N1N1P0, P1P1P0, N1P1P0);
//        V3D_Geometry expResult = P0P0P0;
//        V3D_Geometry result = instance.getIntersection(l, scale, rm);
//        assertEquals(expResult, result);
    }
}
