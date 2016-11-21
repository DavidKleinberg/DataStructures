//David Kleinberg
//dkleinb1@jhu.edu

public class SentinelListTest extends ListTestBase {
	@Override
    protected List<String> createList() {
        return new SentinelList<>();
    }
}