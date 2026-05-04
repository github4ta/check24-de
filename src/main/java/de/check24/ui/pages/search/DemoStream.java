package de.check24.ui.pages.search;

import java.util.ArrayList;
import java.util.List;

public class DemoStream {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");

        // filter()

        List<String> filteredNames = getFilteredNamesByStartsWithA(names);

        System.out.println(filteredNames);




            /*
            String result = java.util.Arrays.stream(names)
                    .filter(name -> name.startsWith("A"))
                    .map(String::toUpperCase)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("No names found");
            System.out.println(result);*/
    }

    private static List<String> getFilteredNamesByStartsWithA(List<String> names) {
        List<String> filteredNames = new ArrayList<>();
        for(int i = 0; i < names.size(); i++){
            if (names.get(i).startsWith("A")){
                filteredNames.add(names.get(i));
            }
        }
        return filteredNames;
    }

    private static List<String> getFilteredNamesByNamesLengthMoreThanFour(List<String> names) {
        List<String> filteredNames = new ArrayList<>();
        for(int i = 0; i < names.size(); i++){
            if (names.get(i).length() > 4){
                filteredNames.add(names.get(i));
            }
        }
        return filteredNames;
    }


}
