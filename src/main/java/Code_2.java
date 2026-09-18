public class Code_2 {
 public static boolean isPalindrome(String str){
     if(str == null){
         return false;
     }

     String strClean = str.toLowerCase();

     int left = 0;
     int right = strClean.length() - 1;

     while(right>left){
         if(strClean.charAt(right) != strClean.charAt(left)){
             return false;
         }
         right--;
         left++;
     }
     return true;
 }

    public static void main(String[] args) {
        String str = "Казак";

        boolean res = isPalindrome(str);
        System.out.println("Результат:" + res);
    }
}
