import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateString {
    private final int minStringSize;
    private final int maxStringSize;
    protected List <String> generatedString = new ArrayList<>();

    public GenerateString(int minStringSize, int maxStringSize) {
        this.minStringSize = minStringSize;
        this.maxStringSize = maxStringSize;
    }

    protected String generateRandomString(Random random, int min, int max) {
        return random.ints(97, 122)
                .map(a->random.nextBoolean()? a-32:a )
                .mapToObj(i -> (char) i)
                .limit(random.nextInt(max-min + 1)+ min)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
    public List<String> getData (int numberWords){
        generatedString =  Stream.generate(()->generateRandomString(new Random(), minStringSize, maxStringSize))
                .limit(numberWords)
                .collect(Collectors.toList());
        return generatedString;
    }
}