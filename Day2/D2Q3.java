package Day2;

public class D2Q3 {
    public static void main(String[] args) {

        //write your answer here
        String str = "Great Learning";
        String sentence = "";

        String [] words = str.split(" ");
        for(String word : words){
            String reversedWord = "";

            for(int i=word.length() -1; i>= 0;i--){
                reversedWord += word.charAt(i);
            }
            sentence += reversedWord+ " ";
        }

        System.out.println(sentence.trim());



    }
}
