package cn.shuaijunlan.leetcode.playground;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 14:43 2017/10/12.
 */
// Please implement the serializer and deserializer for char array below.
// For char arrays, we follow the JSON standard according to http://www.json.org/
// Therefore, a single character A is represented as "A" (wrapped in double quotes instead of single quotes).
// A char array containing 3 elements "A", "B", "C" is represented in string as ["A","B","C"].
// For the purpose of this problem, you must not use JSON parser library or eval method.
// Standard library provided by the language (not including JSON library) is allowed.

class Main1 {
    public static String charArrayToString(char[] param) throws Exception {
        if(param == null || param.length == 0){
            return "[]";
        }
        String str = "";
        for(int i = 0; i < param.length - 1; i++){
            str += "\""+ String.valueOf(param[i]) + "\"" + ",";
        }
        str += "\""+ String.valueOf(param[param.length - 1]) + "\"" ;
        return "[" + str + "]";
    }

    // Bonus point if your deserializer is able to deal with whitespaces between elements.
    // For example: param = "[  \"a\",  \"b\", \"c\"  ]"
    public static char[] stringToCharArray(String param) throws Exception {
        int length = param.length();
        String[] arr = param.split(",");

        throw new Exception("Function Not implemented yet.");
    }

    // Note: These tests are basic and passing them does not mean your code is correct.
    // Feel free to write additional tests and test serializer and deserializer individually.
    public static void main(String[] args) {
        String[] testcases = {
                "[]",
                "[\"a\",\"b\",\"c\"]",
                "[\"T\",\"e\",\"!\",\"'\"]",
                "[\"'\",\"\\\"\",\"c\"]",
                "[\"\\n\",\"\\t\",\"'\",\"\\\"\",\"\\\\\"]"
        };

        for(String testcase : testcases) {
            try {
                if (!charArrayToString(stringToCharArray(testcase)).equals(testcase)) {
                    System.out.println("TESTCASE FAILED : {}" + testcase);
                } else {
                    System.out.println("TESTCASE PASSED");
                }
            } catch(Exception e) {
                System.out.println("Exception occured in testcase : " + testcase);
                break;
            }
        }
    }
}

