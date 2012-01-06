package br.com.caelum.aeris;

import org.jboss.seam.mock.AbstractSeamTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JUnitSeamTest extends AbstractSeamTest {
	// static helper instance to call non static methods in
	// a static method
	private static JUnitSeamTest seamTest = new JUnitSeamTest();
	private static boolean batch = false;

	/*
	 * JUnit test life cycle methods.
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (!batch) {
			seamTest.startContainer();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (!batch) {
			seamTest.stopContainer();
		}
	}

	@Before
	public void setUp() throws Exception {
		setupClass();
		begin();
	}

	@After
	public void tearDown() throws Exception {
		end();
		cleanupClass();
	}

	/*
	 * Helper methods
	 */

	// starts the JBoss embedded ejb container
	public void startContainer() throws Exception {
		super.startSeam();
	}

	// stops the JBoss embedded ejb container
	public void stopContainer() throws Exception {
		super.stopSeam();
	}

	public boolean isBatch() {
		return batch;
	}

	// used by a JUnit.Suite runner to set the batch flag
	public void setBatch(final boolean fBatch) {
		batch = fBatch;
	}
}
