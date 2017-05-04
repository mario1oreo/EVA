
public class CountBottle {

    public static void main(String[] args) {
        int count = 0;
        int money = 10;
        int bottle = 0;
        int cap = 0;
        int price = 2;
        // 10块钱

        // 1.先买
        int buyNum = money / price;
        count += buyNum;
        bottle += buyNum;
        cap += buyNum;
        System.out.println("count:" + count + "\t bottle:" + bottle + "\t cap:" + cap + "\t 先买5瓶");
        while (true) {
            // if (bottle >= 1) {
            // // 赊账一瓶 还老板一个瓶子 多一个瓶盖
            // count += 1;
            // bottle -= 1;
            // cap++;
            // System.out.println("count:" + count + "\t bottle:" + bottle + "\t
            // cap:" + cap + "\t 赊账一瓶 还老板一个瓶子 多一个瓶盖");
            // continue;
            // } else if (cap >= 2) {
            // // 赊账两瓶 给老板2个赊账的瓶子 2个瓶盖
            // count += 2;
            // cap -= 2;
            // System.out.println("count:" + count + "\t bottle:" + bottle + "\t
            // cap:" + cap + "\t 赊账两瓶 给老板2个赊账的瓶子 2个赊账的瓶盖 2个自己的瓶盖");
            // continue;
            // } else
            if (cap >= 4) {
                // 4个瓶盖换一瓶
                cap -= 4;
                count += 1;
                bottle += 1;
                System.out.println("count:" + count + "\t bottle:" + bottle + "\t cap:" + cap + "\t 4个瓶盖换一瓶");
                continue;
            }
            if (bottle >= 2) {
                // 两个空瓶子换一瓶
                bottle -= 2;
                count += 1;
                cap += 1;
                System.out.println("count:" + count + "\t bottle:" + bottle + "\t cap:" + cap + "\t 两个空瓶子换一瓶");
                continue;
                // } else if (cap == 3) {
                // // 赊账一瓶 给老板3个瓶盖 多一瓶子
                // count += 1;
                // cap -= 3;
                // bottle += 1;
                // System.out.println("count:" + count + "\t bottle:" + bottle +
                // "\t cap:" + cap + "\t 赊账一瓶 给老板3个瓶盖 多一瓶子");
                // continue;
            }

        }
    }
}
