public class Test {
    public static void main(String[] args) throws FullTableException, WrongSizeException {
        String first="Ab1c3d",second="adb",third="1ere";
        System.out.println(checkIfTheSameLettersString(first,third));
        System.out.println(checkIfTheSameLetters(first,third));

        /*try {
            MyHashTable<Object> hashTable = new MyHashTable<>(5);
            hashTable.add(1);
            System.out.println(hashTable.contains(1));
            hashTable.add("S");
            System.out.println(hashTable.contains("S"));
            hashTable.add(3);
            System.out.println(hashTable.contains(3));
            //hashTable.remove(1);
            hashTable.add(4);
            System.out.println(hashTable.contains(4));
            hashTable.add(5);
            System.out.println(hashTable.contains(5));
        }
        catch (Exception e){
            e.printStackTrace();
        }

         */
    }
    /** check if the second is made of letters from first */
    public static boolean checkIfTheSameLettersString(String first,String second) throws WrongSizeException, FullTableException {
        if(first.length()<second.length())
            return false;
        first=first.toLowerCase();
        second=second.toLowerCase();

        MyHashTable<Object> hash = new MyHashTable<>(first.length());
        for (int k=0;k<first.length();k++)
            hash.add(first.charAt(k));
        int i=0;
        boolean result=false;
        while(i<second.length()){
            char character=second.charAt(i);
            if(Character.isLetter(character)){
                if(hash.contains(character))
                    result=true;
                else
                    return false;
            }
            i++;
        }
        return result;
    }
    public static boolean checkIfTheSameLetters(String first,String second) throws WrongSizeException, FullTableException {
        if (first.length() < second.length())
            return false;
        first=first.toLowerCase();
        second=second.toLowerCase();
        int i=0;
        boolean result=false;
        while(i<second.length()){
            char character=second.charAt(i);
            if(Character.isLetter(character)){
                if (first.contains(String.valueOf(character)))
                    result=true;
                else
                    return false;
            }
            i++;
        }
        return result;
    }
}
