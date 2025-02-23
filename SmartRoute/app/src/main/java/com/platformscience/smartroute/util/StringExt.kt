package com.platformscience.smartroute.util
/**
 * String class extension functions
 */

/**
 * Returns the number of vowels in the string
 * Note: 'y' is considered a vowel if it is at the end of the word.
 * This function will check if y is at the end of the string or if it is in the middle
 * of the string broken by a whitespace...
 *
 * @TODO:
 * Limitation of determining y is a vowel or consonant:
 * The current implementation assumes y is a vowel if it appears at the end of the string
 * or in the middle of a string with a whitespace character following it.*
 * There are a lot of limitations of this assumption, but addressing this is very complex
 * and beyond the scope of this current implementation
 *
 */

/**
 * If false, 'y' is always treqted as a consonant
 */
const val checkYForVowel = true;

fun String.vowels(): Int {
    var count = 0
    for ((i, ch) in this.lowercase().withIndex()) {
        if (ch == 'a' || ch == 'e' || ch == 'i'
            || ch == 'o' || ch == 'u'
            || (ch == 'y' && checkYForVowel && (i == this.length - 1 || this.get(i + 1).isWhitespace()))
        )
            count++;
    }
    return count;
}

/**
 * returns the number of consonants in the string
 * 'y' is considered a consonant if it is NOT the end of the word.
 * This function will check if is not at the end of the string,
 * or not followed by a whitespace character.
 *
 * @TODO:
 * Limitation of determining y is a vowel or consonant:
 * The current implementation assumes y is a vowel if it appears at the end of the string
 * or in the middle of a string with a whitespace character following it.*
 * There are a lot of limitations of this assumption, but addressing this is very complex
 * and beyond the scope of this current implementation
 */
fun String.consonants(): Int {
    var count = 0
    for ((i, ch) in this.lowercase().withIndex()) {
        if ((ch in 'b' until 'e')
            || (ch in 'f' until 'i')
            || (ch in 'j' until 'o')
            || (ch in 'p' until 'u')
            || (ch in 'v' until 'y')
            || (ch == 'z')
            || (ch == 'y' && (!checkYForVowel || (i != this.length - 1 && !this.get(i + 1).isWhitespace())))
        )
            count++;
    }
    return count;
}