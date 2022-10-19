package BlockChain;

import java.util.Date;

/**
 * @ 陈钦
 * @ 每一个区块里面的信息
 */
@SuppressWarnings({"all"})
class Block {
    public String hash;//这个区块的哈希值
    public String previousHash;//前一个区块的哈希值
    private String data; //本项目，我们的信息将会是一个简单的话语
    private long timeStamp; //as number of milliseconds since 1/1/1970.//时间
    private int nonce;//用来记录了寻找了多少次

    //构造器
    public Block(String data, String previousHash) {//传入数据和前一个哈希值
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();//获取区块生成时的时间：as number of milliseconds since 1/1/1970
        this.hash = calculateHash(); //Making sure we do this after we set the other values.确保我们在设置其他值之后这样做
    }

    //Calculate new hash based on blocks contents
    //根据区块内容来计算这个新区块的新的哈希值
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(//将四个属性转化成String后，放入方法来计算出新的哈希值
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        //新建一个char数组，有difficuy个大小，然后变成String。因为String此时是空白的，把所有空位置变成0
        String target = new String(new char[difficulty]).replace('\0', '0'); //创建一个难度为*“0”的字符串
        //下面while，substring(0,difficulty)，将0到位置diffiy的所有东西取出来成为一个字符串
        //将此字符串和target对比，如果不一样就一直循环
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();//重新计算哈希值
            System.out.println(hash);
        }
        System.out.println("Block Mined!!! : " + hash);

    }
}
