/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/
class Solution {
    public int countSubstrings(String str) {
       if(str == null || str.length() < 1) return 0;
       int count = 0;
       for(int i=0;i<str.length();i++){
           count += countPalindromes(str, i, i);
           count += countPalindromes(str, i, i+1);
       }
       return count;
   }
   
   private int countPalindromes(String str, int s, int e){
       int count = 0;
       while(s>=0 && e<str.length() && str.charAt(s) == str.charAt(e)){
           s--;
           e++;
           count++;
       }
       return count;
   }
}