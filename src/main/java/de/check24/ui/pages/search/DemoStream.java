package de.check24.ui.pages.search;

import java.util.ArrayList;
import java.util.List;

public class DemoStream {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");

        // filter()

        // List<String> filteredNames = getFilteredNamesByStartsWithA(names);

        //System.out.println(filteredNames);

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A")
                )
                .toList();

        System.out.println(filteredNames);
    }

    private static List<String> getFilteredNamesByStartsWithA(List<String> names) {
        List<String> filteredNames = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (name.startsWith("A")) {
                filteredNames.add(names.get(i));
            }
        }
        return filteredNames;
    }

    private static List<String> getFilteredNamesByNamesLengthMoreThanFour(List<String> names) {
        List<String> filteredNames = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).length() > 4) {
                filteredNames.add(names.get(i));
            }
        }
        return filteredNames;
    }


}
