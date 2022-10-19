package BlockChain;

import java.security.MessageDigest;

/**
 * @ 陈钦
 * @ 这个类的方法applySha256():输入一个信息，来生成一个字符串，如果满足相应条件
 *    String hex = Integer.toHexString(0xff & hash[i]);
 *                 if (hex.length() == 1){
 *                     hexString.append('0');
 *                 }
 *   就返回一个0。否则把其他数字加上去。最终形成一大串字符串
 */
@SuppressWarnings({"all"})
class StringUtil {
    //Applies Sha256 to a string and returns the result.
    //将一个字符串通过SHA256来返回一个结果：哈希值
    public static String applySha256(String input) {
        try {
            //getInstance():返回实现指定摘要算法的MessageDigest对象。这里指定的算法为SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input
            /*
            digest.digest():使用指定的字节数组对摘要执行最后一次更新，然后完成摘要计算。
            也就是说，这个方法首先调用update(input)，将输入数组传递给update方法，然后调用digest()。
             */
            /*
            input.getBytes():使用指定的字节数组对摘要执行最后一次更新，然后完成摘要计算。
            也就是说，这个方法首先调用update(input)，将输入数组传递给update方法，然后调用digest()。
            使用指定的字符集将此String编码为一个字节序列，并将结果存储到一个新的字节数组中。
            当此字符串不能用给定的字符集编码时，此方法的行为未指定。
            当需要对编码过程进行更多控制时，应该使用CharsetEncoder类。
             */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); //这将包含十六进制的哈希值
            for (int i = 0; i < hash.length; i++) {
                //toHexString(i)：返回整数参数的字符串表示形式，作为16位中的无符号整数。
                //此方法的返回类型为String ，它返回给定参数的十六进制字符串，该字符串表示无符号long值。

                //&:与运算符用符号“&”表示，其使用规律如下：
                //两个操作数中位都为1，结果才为1，否则结果为0
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}