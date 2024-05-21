package pl.pja.s28201.tpo_10.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class IdAndUrlService {

    private static final List<Character> POSSIBLE_VALUES = Stream.concat(
            "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(c -> (char) c),
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().mapToObj(c -> (char) c)
    ).toList();

    private static final String DEFAULT_URL_PATH = "http://localhost:8080/red/";

    public String generateId() {
        var rand = new Random();
        StringBuilder resultSequence = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            var character = POSSIBLE_VALUES.get(rand.nextInt(0, POSSIBLE_VALUES.size()));
            resultSequence.append(character);
        }

        return resultSequence.toString();
    }

    public String getDefaultUrlPath() {
        return DEFAULT_URL_PATH;
    }
}
