package org.njit.cs.junit.java.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JUnitAppTest 
    extends TestCase
{

    public JUnitAppTest( String testName )
    {
        super( testName );
    }
    public static Test suite()
    {
        return new TestSuite( JUnitAppTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
