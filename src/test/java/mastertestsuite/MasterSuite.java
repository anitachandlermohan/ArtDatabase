package mastertestsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.integration.GalleryIntegrationTest;
import test.integration.PieceIntegrationTest;
import test.repo.GalleryRepoTest;
import test.repo.PieceRepoTest;

@RunWith(Suite.class)
@SuiteClasses({GalleryRepoTest.class, PieceRepoTest.class, GalleryIntegrationTest.class, PieceIntegrationTest.class})
public class MasterSuite {

}
