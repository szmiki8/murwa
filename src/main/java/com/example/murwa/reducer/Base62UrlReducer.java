package com.example.murwa.reducer;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Component
public class Base62UrlReducer implements UrlReducer {

    private static final List<Character> CHARACTERS = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++)
            CHARACTERS.add(Character.forDigit(i, 10));

        for (int i = 97; i < 123; i++)
            CHARACTERS.add((char)i);

        for (int i = 65; i < 91; i++)
            CHARACTERS.add((char)i);
    }

    private static Long BASE = 62L;

    @Override
    public String reduce(Long id) {
        Deque<Integer> stack = dispiece(id);

        return format(stack);
    }

    private Deque<Integer> dispiece(Long id) {
        Deque<Integer> stack = new ArrayDeque<>();

        while (id >= BASE) {
            Long mod = id % BASE;
            id = id / BASE;

            stack.push(mod.intValue());
        }

        stack.push(id.intValue());

        return stack;
    }

    private String format(Deque<Integer> stack) {
        StringBuilder sb = new StringBuilder();

        for (Integer i : stack) {
            sb.append(CHARACTERS.get(i));
        }

        return sb.toString();
    }

}
