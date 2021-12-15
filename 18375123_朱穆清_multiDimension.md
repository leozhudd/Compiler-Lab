## 编译实验Lab-c2：多维数组

这次实验没什么可说的，我在lab7写数组的时候就已经是按照多维数组的方式来实现的，做到这里debug一下就通过了。

简单说一下我构建高维全局数组的表达式的方法：

单独定义一个函数递归来生成高维数组的类型+数值，因为维数是不确定的，只有遍历到的时候才知道有多少维，因此不能用固定次循环的方式，只能递归调用。每递归一层，就进入一个新的中括号内，此时再和上一层一样，分析并输出数组类型和数值，如果数值是数组，说明还有维数没有分析完，那么就继续递归调用。

```java
    private void construct(ArrayList<Integer> arrayDim, String type, int nowDim, int totalIndex) {
        /**
         * 递归构建任意多维全局数组定义表达式（等号右边的部分）
         * 1维数组：[3 x i32] [i32 1, i32 2, i32 3]
         * 2维数组：[2 x [2 x i32]] [[2 x i32] [i32 1, i32 2], [2 x i32] [i32 3, i32 0]]
         */
        if(nowDim == arrayDim.size()) { // 已经是最后一维了，输出"i32 num"
            // System.out.print(" <i32 " + totalIndex + "> ");
            // 此时的totalIndex是这个元素在数组中的位置唯一编号
            if (globalArrayInitVals.containsKey(totalIndex)) {
                System.out.print("i32 " + globalArrayInitVals.get(totalIndex));
            }
            else {
                System.out.print("i32 0"); // 没有提供初始值，默认为0
            }
        }
        else {
            String nextType = type.substring(5, type.length() - 1);
            System.out.print(type + " [");
            for (int i = 0; i < arrayDim.get(nowDim); i++) {
                construct(arrayDim, nextType, nowDim + 1, totalIndex * arrayDim.get(nowDim) + i);
                if (i != arrayDim.get(nowDim) - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
        }
    }
```

类似的，还有一个函数用来缩小数组的类型（在函数传参时需要缩小一维），通过简单的字符串操作来实现，每调用一次，arraytype就减少一维，我封装了一下：

```java
public static String splitArrayType(String type) {
        int len = type.length();
        if(type.charAt(len-2) == ']') { // [99999 x [99999 x i32]]
            int i = 1;
            for(;i<len;i++) {
                if(type.charAt(i) == '[') {
                    break;
                }
            }
            return type.substring(i, len-1);
        }
        else { // [99999 x i32]
            return type.substring(len-4, len-1);
        }
    }
```

高维局部数组定义和访问和普通数组基本一致，有多少维，下标算出来之后在`getelementptr`后面输出多少个`, i32 xxx`即可获得每个元素的地址。

另外此次修复了两个之前没有发现的bug：

1. 定义常量时，之前我只在符号表中保存了常量所在的寄存器，但没保存数值。这导致了需要压缩表达式时（获取数组定义的维度长度）无法直接得到常量的值，而我又不想额外输出一个load。因此额外保存了常量数值后即可实现。
2. 在函数调用时数组作为参数传递时，我之前的getelementptr中的维度顺序有误，其中一个`i32 0`应该放在第一个位置，而不是最后一个。最后一个位置上无论最后得到的数组还是元素，都需要用一个`i32 0`来降一维。我重新梳理了这一块的逻辑并修改了代码。
