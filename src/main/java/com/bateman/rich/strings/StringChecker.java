package com.bateman.rich.strings;

import java.util.HashMap;

/**
 * Class containing string-related solutions to Cracking the Coding Interview.
 */
public class StringChecker {
    /**
     * Problem 1.1
     * Does the input string only contain distinct characters?
     * Assume ASCI character set (128 characters).
     * @param input
     * @return
     */
    public static boolean doesStringContainOnlyDistinctCharacters(String input) {
        boolean result = true; // assume true, and prove false
        final int ASCII_SET_SIZE = 128;
        if(input.length() > ASCII_SET_SIZE) {
            // If the string is longer than the size of the set of ASCII characters,
            // then at least one character must be repeated.
            result = false;
        } else {
            boolean[] charSet = new boolean[ASCII_SET_SIZE];
            for(int i = 0; i < input.length();i++) {
                int charVal = input.charAt(i);
                if(charSet[charVal]) {
                    result = false;
                    break;
                } else {
                    charSet[charVal]=true;
                }
            }
        }
        return result;
    }

    /**
     * 1.2  Is one string a permutation of the other.
     * @param a
     * @param b
     * @return
     */
    public static boolean isStringPermutationOfOther(String a, String b) {
        if(a.length() != b.length()) return false; // If strings are different length, they cannot possibly be permutations of the other.
        HashMap<Integer, Integer> charCounts = new HashMap<>();
        // Get a count of all characters in a.
        for(int i = 0; i < a.length(); i++) {
            int charVal = a.charAt(i);
            Integer currentCount = charCounts.get(charVal);
            if(currentCount == null) {
                charCounts.put(charVal, 1);
            } else {
                charCounts.put(charVal, currentCount + 1);
            }
        }
        // Loop over b, decrementing the counts in our map.  If at ANY point, we dip into the negative (or don't find a character)
        // we know b has a character not in a.  Since these strings are equal length, the only way to not go negative
        // is to match each character.
        for(int i = 0; i < b.length(); i++) {
            int charVal = b.charAt(i);
            Integer charCount = charCounts.get(charVal);
            if(charCount == null) {
                return false;
            } else {
                charCount--;
                if(charCount < 0) return false;
                charCounts.put(charVal, charCount);
            }
        }
        return true;
    }

    /**
     * 1.3.  Given an input of characters, replace all spaces with "%20".  Assume there is enough space
     * in the character array to handle these extra characters.  The "trueLength" represents the actual
     * length of the input string, not including these extra spaces in the input buffer.  There also needs to be space
     * for a null-terminating character. trueLength includes null character
     * @param input
     * @param trueLength
     */
    public static void urlify(char[] input, int trueLength) {
        int numSpaces = 0;
        for(int i = 0; i < trueLength; i++) {if(input[i] == ' ') numSpaces++;}
        // Update the buffer BACKWARDS (since the end spaces are fine to erase).
        int workingIndex = trueLength + 2 * numSpaces - 1;
        if(trueLength < workingIndex) input[trueLength] = '\0';
        for(int i = trueLength - 1; i >= 0; i--) {
            if(input[i] == ' ') {
                input[workingIndex] = '0';
                input[workingIndex-1] = '2';
                input[workingIndex-2] = '%';
                workingIndex-=3;
            } else {
                input[workingIndex] = input[i];
                workingIndex--;
            }
        }
    }
}
