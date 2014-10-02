package com.vmware.rdo.telemetry.utils;

import static org.junit.Assert.*;

import org.balaji.exception.BalajiException;
import org.balaji.utils.ZipUtil;
import org.junit.Test;

public class ZipUtilTest {

	@Test
	public void testUnzip() {
		
	}
	
	@Test
	public void testFileNotFoundException() {
		boolean thrown= false;
		
		try {
			ZipUtil.unzip("\\root\\dummy.zip", "temp");
		} catch (BalajiException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}

}
