/*
 * Class: CMSC203
 * Instructor:Dr. Grinberg
 * Description: (Give a brief description for each Class)
 * Due: 7/10/2023
 * Platform/compiler:
 * I pledge that I have completed the programming  assignment independently.
 *  I have not copied the code from a student or any source.
 *  I have not given my code to any student.
 *  Print your Name here: _Saron Endale_________
 */

package com.example.assignmnet3;

/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple Ã¯Â¿Â½substitution cipherÃ¯Â¿Â½ where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 *
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;


    /**
     * This method determines if a string is within the allowable bounds of ASCII codes
     * according to the LOWER_RANGE and UPPER_RANGE characters
     * @param plainText a string to be encrypted, if it is within the allowable bounds
     * @return true if all characters are within the allowable bounds, false if any character is outside
     */
    public static boolean isStringInBounds (String plainText) {
        boolean isInBounds = true;
        char[] plainTextCharacters = plainText.toCharArray();
        for(char character : plainTextCharacters){
            if (!((character >= LOWER_RANGE) && (character <= UPPER_RANGE))){

                //lower ---- upper
                isInBounds = false;
            }
        }
        return isInBounds;
    }

    /**
     * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
     * and each character in plainText is replaced by the character \"offset\" away from it
     * @param plainText an uppercase string to be encrypted.
     * @param key an integer that specifies the offset of each character
     * @return the encrypted string
     */
    public static String caesarEncryption(String plainText, int key) {
        if(!isStringInBounds(plainText)){
            return "The selected string is not in bounds, Try again.";
        }
        if(key > 26){
            key = (key % 26) + 26;
        }
        String encryptedText = "";
        for(char character : plainText.toCharArray()){
            char c = (char)(character + key);
            if(c > 'Z'){
                encryptedText += (char)(character - (26 - key));
            }
            else{
                encryptedText += c;
            }
        }
        return encryptedText;
    }




    /**
     * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset
     * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
     * to correspond to the length of plainText
     * @param plainText an uppercase string to be encrypted.
     * @param bellasoStr an uppercase string that specifies the offsets, character by character.
     * @return the encrypted string
     */
    public static String bellasoEncryption (String plainText, String bellasoStr) {
        char[] bellasoStrCharArray = bellasoStr.toCharArray();
        bellasoStr = String.valueOf(bellasoStrCharArray);
        int i,j;
        char plainTextCharArray[] = plainText.toCharArray();

        int bellasoStrLength = plainTextCharArray.length;
        char encryptedText[] = new char[bellasoStrLength];
        char encryptionCode[] = new char[bellasoStrLength];

        for(i = 0, j = 0; i < bellasoStrLength; ++i, ++j) {
            if(j == bellasoStr.length()) {
                j = 0;
            }
            encryptionCode[i] = bellasoStr.charAt(j);
        }
        for(char character : plainTextCharArray){
            if(Character.isLetter(character)){
                for(i = 0; i < bellasoStrLength; ++i){
                    encryptedText[i] = (char) (((plainTextCharArray[i] + encryptionCode[i]) % 26) + 'A');
                }
            }
        }
        String encryptedString = String.valueOf(encryptedText);
        return encryptedString;
    }

    /**
     * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
     * and each character in encryptedText is replaced by the character \"offset\" characters before it.
     * This is the inverse of the encryptCaesar method.
     * @param encryptedText an encrypted string to be decrypted.
     * @param key an integer that specifies the offset of each character
     * @return the plain text string
     */
    public static String caesarDecryption (String encryptedText, int key) {
        if(!isStringInBounds(encryptedText)){
            return "The selected String is not in bounds. Please try again!";
        }
        if(key > 26){
            key = (key % 26) + 26;
        }
        String decryptedText = "";

        for(char character : encryptedText.toCharArray()){
            if(Character.isLetter(character)){
                char c = (char)(character - key);
                if(c < 'A'){
                    decryptedText += (char)(character - (26 + key));
                }
                else{
                    decryptedText += c;
                }
            }
        }
        return decryptedText;
    }

    /**
     * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
     * the character corresponding to the character in bellasoStr, which is repeated
     * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
     * @param encryptedText an uppercase string to be encrypted.
     * @param bellasoStr an uppercase string that specifies the offsets, character by character.
     * @return the decrypted string
     */
    public static String bellasoDecryption(String encryptedText, String bellasoStr) {
        int i, j;
        char encryptedTextCharArray[] = encryptedText.toCharArray();
        int bellasoStrLength = encryptedTextCharArray.length;
        char decryptedText[] = new char[bellasoStrLength];
        char decryptionCode[] = new char[bellasoStrLength];

        for (i = 0, j = 0; i < bellasoStrLength; ++i, ++j) {
            if (j == bellasoStr.length()) {
                j = 0;
            }
            decryptionCode[i] = bellasoStr.charAt(j);
        }
        for (char character : encryptedTextCharArray) {
            if (Character.isLetter(character)) {
                for (i = 0; i < bellasoStrLength; ++i) {
                    decryptedText[i] = (char) ((((encryptedTextCharArray[i] - decryptionCode[i]) + 26) % 26) + 'A');
                }
            }
        }
        String decryptedString = String.valueOf(decryptedText);
        return decryptedString;
    }
}
