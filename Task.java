import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

public class Task {
    public static void main(String[] args) {
        List<phoneBook> phone = Arrays.asList(
                new phoneBook("Sokolov", "8 930 300 10 10"),
                new phoneBook("Voronin", "8 910 100 20 20"),
                new phoneBook("Morozova", "8 915 300 10 10"),
                new phoneBook("Voronin", "43 86 96"),
                new phoneBook("Morozova", "35 10 15"),
                new phoneBook("Morozova", "155"));
        Map<String, List<String>> multimap = new HashMap<>();
        for (phoneBook people : phone) {
            multimap.computeIfAbsent(people.getPerson(), k -> new ArrayList<>()).add(people.getPhone());
        }
        System.out.println("Телефонная книга: ");
        System.out.println(multimap + "\n");
        Map<String, List<String>> phoneBook = multimap.entrySet().stream()
                .sorted(comparingInt(e -> e.getValue().size()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new));
        Set<String> set = phoneBook.keySet();
        Iterator<String> itr = set.iterator();
        List<String> alKeys = new ArrayList<String>(phoneBook.keySet());
        Collections.reverse(alKeys);
        System.out.println("Телефонная книга отсортированная: ");
        for (String key : alKeys) {
            System.out.println(key + " " + phoneBook.get(key));
        }
    }
}