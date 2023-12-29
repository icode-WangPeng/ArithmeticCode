package GroupThree.telephoneNumber;

import java.util.*;

/**
 * &#064;author: YAR <BR/>
 * &#064;time: 2023/12/18 9:31 <BR/>
 * &#064;className: telephoneNum <BR/>
 * &#064;description
 ***/
public class telephoneNum_zhijie {

    //数字与字母映射
    public static Map<Integer,String> constructNum(){
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        return map;
    }

    //直接法
    public static List<String> letterCombinations(String digits) {
        HashMap<Integer, String> map = (HashMap<Integer, String>) constructNum();
        List<String> list = new ArrayList<String>();

        if (digits.length() == 0)
            return list;
        for (int i = 0; i < map.get(digits.charAt(0) - 48).length(); i++) {
            list.add(map.get(digits.charAt(0) - 48).substring(i, i + 1));
        }
        for (int i = 1; i < digits.length(); i++) {
            String str = map.get(digits.charAt(i) - 48);
            int len = list.size();
            for (int k = 0; k < len; k++) {
                String s = list.get(0);
                list.remove(0);
                for (int m = 0; m < str.length(); m++) {
                    list.add(s + str.substring(m, m + 1));
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("输入数字范围是2~9");
        System.out.print("您要输入的数字字符串是:");
        Scanner scanner=new Scanner(System.in);
        String digits= scanner.nextLine();
        System.out.print("获得的字母组合结果为:");
        System.out.println(letterCombinations(digits));
    }

}
