package com.cashcrusaders.findkeywords;

import java.util.*;
import java.util.regex.Pattern;

public class FindKeywords {

    private static List<String> setupListOfItems() {
        return Arrays.asList( "Users", "User Groups", "User Activity Log");
    }

    private static List<String> setupSearchKeywords() {
        return Arrays.asList("u", "u gr", "u l");
    }

    public static void main(String[] args) {

        List<String> items = setupListOfItems();
        List<String> keywords = setupSearchKeywords();

        Map matched =findKeywords(items, keywords);

        System.out.println(matched);
    }

    private static Map findKeywords(List<String> items, List<String> keywords ) {
        Map<String,List<String>> matched = new HashMap<>();

        keywords.parallelStream().forEach(key ->{

            String pattern =".*(?i)"+ key.replace(" ",".*(?i)");
            Pattern compile =Pattern.compile(pattern);

            items.forEach(item-> {
                if(compile.matcher(item).find()) {
                    if(!matched.containsKey(key)) {
                        matched.put(key, new ArrayList<>());
                    }
                    matched.get(key).add(item);
                };
            });
        });
        return matched;
    }

}
