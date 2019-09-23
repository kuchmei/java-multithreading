import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static java.util.stream.Collectors.toList;


public class FilterString extends RecursiveTask<List<String>> {
    private final int threshold;
    protected final List <String> data ;
    protected List <String> filteredString = new ArrayList<>();

    public FilterString(List<String> data, int threshold) {
        this.data = data;
        this.threshold = threshold;
    }

    protected List <String> filterGenerateString (){
        filteredString =  data.stream()
                .filter(string -> string.matches("(.*([AEIOUYaeiouy]).*){2}"))
                .collect(toList());
        return filteredString;
    }

    @Override
    protected List<String> compute() {
        if (getSurplusQueuedTaskCount()< threshold) {
            return filterGenerateString();
        }
        int mid = data.size() >>> 1;
        FilterString firstSubTask = new FilterString(data.subList(0,mid), threshold);
        FilterString secondSubTask = new FilterString(data.subList(mid, data.size()), threshold);
        firstSubTask.fork();
        secondSubTask.fork();

        filteredString.addAll(secondSubTask.invoke());
        filteredString.addAll(firstSubTask.join());

        return filteredString;
    }
}