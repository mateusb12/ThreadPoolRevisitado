import java.util.ArrayList;
import java.util.List;

public class RacingCondition implements SharedListManager{
    public static final List<Integer> sharedList = new ArrayList<Integer>();

    @Override
    public List<Integer> getSharedList() {
        return sharedList;
    }

    public void addToSharedList(int num) {
        sharedList.add(num);
    }

    public static void main(String[] args) throws InterruptedException {
        CoreInsertion insertionInstance = new CoreInsertion();
        RacingCondition rc = new RacingCondition();
        insertionInstance.run(rc);
    }
}

