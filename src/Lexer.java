import java.io.*;
import java.util.*;

public class Lexer {
    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s;
            // 每次读一行
            while((s = in.readLine()) != null){
                if(judge(s) == -1)
                    break;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int judge(String str){

        TreeMap<Character,String> delimiter = new TreeMap<>();
        delimiter.put('=', "Assign");
        delimiter.put('+', "Plus");
        delimiter.put('/', "Div");
        delimiter.put('*', "Mult");
        delimiter.put('<', "Lt");
        delimiter.put('>', "Gt");
        delimiter.put(';', "Semicolon");
        delimiter.put('(', "LPar");
        delimiter.put(')', "RPar");
        delimiter.put('{', "LBrace");
        delimiter.put('}', "RBrace");

        char[] s = str.toCharArray();
        for(int i=0;i<s.length;i++) {
            char c = s[i];
            if (isLetter(c)) { // 如果是字母开头
                StringBuilder buildStr = new StringBuilder();
                for(;i<s.length && (isDigit(s[i]) || isLetter(s[i]));i++){
                    buildStr.append(s[i]);
                }
                i--;
                // 判断是否为保留字
                if(buildStr.toString().equals("if"))
                    System.out.println("If");
                else if(buildStr.toString().equals("else"))
                    System.out.println("Else");
                else if(buildStr.toString().equals("while"))
                    System.out.println("While");
                else if(buildStr.toString().equals("continue"))
                    System.out.println("Continue");
                else if(buildStr.toString().equals("return"))
                    System.out.println("Return");
                else if(buildStr.toString().equals("break"))
                    System.out.println("Break");
                else System.out.printf("Ident(%s)\n",buildStr);
            }
            else if(isDigit(c)) { // 如果是数字
                StringBuilder buildStr = new StringBuilder();
                for(;i<s.length && isDigit(s[i]);i++){
                    buildStr.append(s[i]);
                }
                i--;
                System.out.printf("Number(%s)\n",buildStr);
            }
            else if(delimiter.containsKey(c)) { // 判断分界符
                if(c=='=' && s[i+1]=='=') { // 特判双等号
                    System.out.println("Eq");
                    i++;
                }
                else System.out.println(delimiter.get(c));
            }
            else if(isSpace(c)){
            }
            else {
                System.out.println("Err");
                return -1;
            }
        }
        return 0;
    }

    private static boolean isSpace(char c) {
        return c==' ' || c=='\t';
    }
    public static boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }
    public static boolean isLetter(char c){
        return (c>='a'&&c<='z') || (c>='A'&&c<='Z');
    }


}