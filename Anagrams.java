package chewse;

import java.util.*;

/**
 * Created by kbozoglu on 8/12/17.
 * String anagrams(String s) method must be called
 * If this function can not find two words in the text that are anagrams of another two words, returns empty string.
 * If there are multiple pair of anagram pairs, returns the first apperance

//Anagrams
//
//        Create a function that finds two words in a text that are anagrams of another two words in that text. For example:
//
//        Happy eaters always heat their yappers.
//        Would yield: happy eaters and heat yappers
//
//        The function should also work on long blocks of text.
//
//        Rules:
//        - Treat all letters as lowercase.
//        - Ignore any words less than 4 characters long.
//        - Treat all non-alpha-numeric characters as whitespace.
//        - So "Surely. And they're completely right!" becomes four words: "surely  they completely right"
//        - Neither of the words in the first pair can be repeated in the second pair.
 */

public class Anagrams {

    public static String anagrams(String s){
        List<String> validWords = getValidWords(s);
        int len = validWords.size();
        if(len < 4) return "";

        for(int i = 0; i < len; i++){
            for(int j = i+1; j < len; j++){
                for(int x = 0; x < len; x++){
                    for(int y = x+1; y < len; y++){
                        if(x != i && x != j && y != i && y != j){
                            if(isAnagramPairs(validWords.get(i), validWords.get(j), validWords.get(x), validWords.get(y))){
                                return validWords.get(i) + " " + validWords.get(j) + " and " + validWords.get(x) + " " + validWords.get(y);
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    //returns a list of unique words according to the rules
    private static List<String> getValidWords(String s){
        String[] allWords = s.split("[^a-zA-Z0-9]+");
        Set<String> setOfValidWords = new HashSet<>(); //all the unique and valid words in a set
        // keeping the uniq words in a list if the order is important in the result string?
        // if the order is now important the setOfValidWords is enough no need this list.
        List<String> validWords = new ArrayList<>();
        for(String word : allWords){
            if(word.length() > 3 && !validWords.contains(word)){
                validWords.add(word.toLowerCase());
                setOfValidWords.add(word);
            }
        }
        return validWords;
    }

    private static boolean isAnagramPairs(String s1, String s2, String s3, String s4){
        Map<Character, Integer> numOfLetters = new HashMap<>();

        putNumOfLettersInFirstPair(s1, numOfLetters);
        putNumOfLettersInFirstPair(s2, numOfLetters);

        decrementNumOfLettersInMap(s3,numOfLetters);
        decrementNumOfLettersInMap(s4,numOfLetters);

        for(Character ch: numOfLetters.keySet()){
            if(numOfLetters.get(ch) != 0) return false;
        }

        return true;
    }

    private static void putNumOfLettersInFirstPair(String s, Map<Character, Integer> map){
        for(int i = 0; i < s.length(); i++){
            Character ch = s.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch, 1);
            }
            else{
                map.put(ch, map.get(ch) + 1);
            }
        }
    }

    private static void decrementNumOfLettersInMap(String s, Map<Character, Integer> map){
        for(int i = 0; i < s.length(); i++){
            Character ch = s.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch, -1);
            }
            else{
                map.put(ch, map.get(ch) - 1);
            }
        }
    }


    public static void main(String[] args){
        String s1 = "Happy eaters always heat their yappers.";
        String s2 = "Surely";
        String s3 = "geek's treders: Kevser's favorite cusine is the Mediterian cusine but she also likes-- Thai food and Japanese food soo much." +
                "Turkish and Persian cusines have many common food like kebab, stew, baklava.. Ah!.. baklava- such a delicious desert." +
                "Even if the origin of baklava is Turkey, many people recognizes it as a desert from Greek.";

        String result1 = anagrams(s1);
        String result2 = anagrams(s2);
        String result3 = anagrams(s3);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
