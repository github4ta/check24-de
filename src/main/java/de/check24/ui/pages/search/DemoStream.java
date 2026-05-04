package de.check24.ui.pages.search;

import java.util.ArrayList;
import java.util.List;

public class DemoStream {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");

        // filter()

        // List<String> filteredNames = getFilteredNamesByStartsWithA(names);

        //System.out.println(filteredNames);

//        List<String> filteredNames = names.stream()
//                .filter(name -> name.startsWith("A")
//                )
//                .toList();

        MyFunction myFunction = (name) -> {
            return name.startsWith("A");
        };

        List<String> filteredNames = getFilteredNamesByCondition(names, myFunction);

        System.out.println(filteredNames);

        myFunction = (name) -> {
            return name.length() > 4;
        };

        filteredNames = getFilteredNamesByCondition(names, myFunction);
        System.out.println(filteredNames);
    }

    private static List<String> getFilteredNamesByCondition(List<String> names, MyFunction myFunction) {
        List<String> filteredNames = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (myFunction.condition(name)) {
                filteredNames.add(names.get(i));
            }
        }
        return filteredNames;
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
