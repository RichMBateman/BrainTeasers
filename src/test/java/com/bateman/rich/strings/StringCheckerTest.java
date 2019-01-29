package com.bateman.rich.strings;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCheckerTest {

    @Test
    public void doesStringContainOnlyDistinctCharacters() {
        Assert.assertEquals(true, StringChecker.doesStringContainOnlyDistinctCharacters("abcd"));
        Assert.assertEquals(true, StringChecker.doesStringContainOnlyDistinctCharacters("dcba"));
        Assert.assertEquals(false, StringChecker.doesStringContainOnlyDistinctCharacters("aabb"));
        Assert.assertEquals(false, StringChecker.doesStringContainOnlyDistinctCharacters("abcdeab"));
    }

    @Test
    public void isStringPermutationOfOther() {
        Assert.assertEquals(true, StringChecker.isStringPermutationOfOther("abcd", "dbca"));
        Assert.assertEquals(true, StringChecker.isStringPermutationOfOther("taco cat", "actocat "));
        Assert.assertEquals(false, StringChecker.isStringPermutationOfOther("abcd", "dbcae"));
        Assert.assertEquals(false, StringChecker.isStringPermutationOfOther("taco cat", "actocat"));
    }

    @Test
    public void urlify() {
        char[] input = {'h','i', ',', ' ', 'R', 'i', 'c', 'h', '\0', ' ', ' '};
        StringChecker.urlify(input, 9);
        Assert.assertArrayEquals(new char[]{'h','i', ',', '%', '2', '0', 'R', 'i', 'c', 'h', '\0'}, input);

        input = new char[] {'a',' ', 'b', ' ', 'c', ' ', 'd', '\0', ' ', ' ', ' ', ' ', ' ', ' '};
        StringChecker.urlify(input, 8);
        Assert.assertArrayEquals(new char[]{'a','%', '2', '0', 'b','%', '2', '0','c','%', '2', '0','d','\0'}, input);
    }
}