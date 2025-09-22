package org.example.game.rules;

import java.util.List;
import java.util.Objects;

public class Extenstions{
    public static <T> boolean StartsFrom(List<T> list1, List<T> list2) {
        if (list1.size() <= list2.size()) {
            return false;
        }

        for (int i = 0; i < list2.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }

        return true;
    }
}
