package test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GalleryIntegrationTest.class, PieceIntegrationTest.class })
public class IntegrationTest {

}
