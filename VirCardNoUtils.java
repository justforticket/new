import java.text.SimpleDateFormat;
import java.util.*;

public class VirCardNoUtils {


    // 获取贷记卡BIN
    private static List<String> creditCardBins = null;
    /**
     * 分割符
     */
    public static final String SPLIT_CHARACTER = ",";
    /**
     * 月份
     */
    public final static String MONTH = "01,02,03,04,05,06,07,08,09,10,11,12";

    public static String DEFAULT_VIR_BINS_CREDIT="CCB:建设银行:622707,CCB:建设银行:625955,CCB:建设银行:625956,CCB:建设银行:436728,CCB:建设银行:453242,CCB:建设银行:491031,CCB:建设银行:628316,CCB:建设银行:628317,CCB:建设银行:622708,CCB:建设银行:622675,"
            + "ICBC:工商银行:427019,ICBC:工商银行:427020,ICBC:工商银行:427029,ICBC:工商银行:427030,ICBC:工商银行:427039,ICBC:工商银行:438125,ICBC:工商银行:438126,ICBC:工商银行:451804,ICBC:工商银行:427010,ICBC:工商银行:427018,"
            + "ABC:农业银行:403361,ABC:农业银行:404117,ABC:农业银行:404118,ABC:农业银行:404119,ABC:农业银行:404120,ABC:农业银行:404121,ABC:农业银行:463758,ABC:农业银行:514027,ABC:农业银行:519412,ABC:农业银行:519413,"
            + "BOC:中国银行:356835,BOC:中国银行:409665,BOC:中国银行:409666,BOC:中国银行:409672,BOC:中国银行:512315,BOC:中国银行:512316,BOC:中国银行:512411,BOC:中国银行:512412,BOC:中国银行:514957,BOC:中国银行:409667"
            + "CMBCHINA:招商银行:356890,CMBCHINA:招商银行:439188,CMBCHINA:招商银行:439227,CMBCHINA:招商银行:479229,CMBCHINA:招商银行:521302,CMBCHINA:招商银行:356889,CMBCHINA:招商银行:552534,CMBCHINA:招商银行:622575,CMBCHINA:招商银行:622576,CMBCHINA:招商银行:622581"
            + "GDB:广发银行:406365,GDB:广发银行:406366,GDB:广发银行:487013,GDB:广发银行:491032,GDB:广发银行:491035,GDB:广发银行:491037,GDB:广发银行:491038,GDB:广发银行:518364,GDB:广发银行:622558,GDB:广发银行:622559";

    private static Random strGen = new Random();
    /**
     * 随机数据池
     */
    private static char[] numbersAndLetters = ("0123456789").toCharArray();

    static {
        // 加载卡BIN配置
        creditCardBins = new ArrayList<String>();
        // 信用卡配置加载
        String[] banks = DEFAULT_VIR_BINS_CREDIT.split(",");
        for (String bankinfo : banks) {
            String[] info = bankinfo.split(":");
            if (info.length != 3){
                continue;
            }
            creditCardBins.add(bankinfo);
        }
    }




    /**
     * 获取卡bin
     * @return
     */
    private static String[] getCardBinInfo() {
        List<String> cardBins = creditCardBins;
        int index = (int) (Math.random() * cardBins.size());
        String cardbin = cardBins.get(index);
        return cardbin.split(":");
    }

    /**
     * 获取cardbean
     * @return
     */
    public static BankCardBean getBankCardBean(){
        String[] bin = getCardBinInfo();
        String cardNo = bin[2] +randomString(10);
        BankCardBean bankCard = new BankCardBean();
        bankCard.setCardNo(cardNo);
        bankCard.setBankId(bin[0]);
        bankCard.setBankName(bin[1]);
        bankCard.setCvv(getCvv());
        bankCard.setValidityPeriod(getMonth() + getYear());

        //获取持卡人姓名
        String holderName = getName();
        bankCard.setHolderName(holderName);
        //获取持卡人手机号
        String phoneNumber = getTel();
        bankCard.setPhoneNumber(phoneNumber);
        //获取身份证号码
        String idCardNo = getRandomID();
        bankCard.setHolderIdCardNo(idCardNo);

        return bankCard;
    }


    /**
     * 获取三位cvv
     * @return
     */
    public static String getCvv(){
        return randomString(3);
    }

    /**
     * 获取明后2年的年份后两位随机数
     */
    public static String getYear(){
        SimpleDateFormat SHORTDATEFORMAT = new SimpleDateFormat("yyyyMMdd");
        String thisYearStrng = SHORTDATEFORMAT.format(new Date()).substring(2, 4);
        StringBuilder sb = new StringBuilder();
        int thisYear = Integer.valueOf(thisYearStrng).intValue();
        sb.append(thisYear+1)
                .append(SPLIT_CHARACTER)
                .append(thisYear+2);
        return sb.toString().split(SPLIT_CHARACTER)[(int)(Math.random() * 2)];

    }

    /**
     * 获取随机月份
     * @return
     */
    public static String getMonth(){
        String[] months = MONTH.split(SPLIT_CHARACTER);
        return months[(int)(Math.random() * (months.length))];
    }

    /** * 产生随机字符串 * */
    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[strGen.nextInt(9)];
        }
        return new String(randBuffer);
    }

    /**
     * 获取中国人姓名
     *
     * @return
     */
    public static String getName() {
        Random random = new Random();
        String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
                "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
                "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
                "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
        String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
        String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
        int index = random.nextInt(Surname.length - 1);
        //获得一个随机的姓氏
        String name = Surname[index];
        //可以根据这个数设置产生的男女比例
        int i = random.nextInt(3);
        if (i == 2) {
            int j = random.nextInt(girl.length() - 2);
            if (j % 2 == 0) {
                name = "女-" + name + girl.substring(j, j + 2);
            } else {
                name = "女-" + name + girl.substring(j, j + 1);
            }

        } else {
            int j = random.nextInt(girl.length() - 2);
            if (j % 2 == 0) {
                name = "男-" + name + boy.substring(j, j + 2);
            } else {
                name = "男-" + name + boy.substring(j, j + 1);
            }

        }

        return name;
    }

    /**
     * 获取手机号
     * @return
     */
    public static String getTel() {
        String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }


    /**
     * 获取身份证号
     * @return
     */
    public static String getRandomID() {
        String id = "420222199204179999";
        // 随机生成省、自治区、直辖市代码 1-2
        String provinces[] = { "11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82" };
        String province = randomOne(provinces);
        // 随机生成地级市、盟、自治州代码 3-4
        String city = randomCityCode(18);
        // 随机生成县、县级市、区代码 5-6
        String county = randomCityCode(28);
        // 随机生成出生年月 7-14
        String birth = randomBirth(20, 50);
        // 随机生成顺序号 15-17(随机性别)
        String no = new Random().nextInt(899) + 100+"";
        // 随机生成校验码 18
        String checks[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X" };
        String check = randomOne(checks);
        // 拼接身份证号码
        id = province + city + county + birth + no + check;
        return id;
    }

    /**
     * 随机生成minAge到maxAge年龄段的人的生日日期
     *
     * @author mingzijian
     * @param minAge
     * @param maxAge
     * @return
     */
    public static String randomBirth(int minAge, int maxAge) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());// 设置当前日期
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + new Random().nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());
    }

    /**
     * 随机生成两位数的字符串（01-max）,不足两位的前面补0
     *
     * @author mingzijian
     * @param max
     * @return
     */
    public static String randomCityCode(int max) {
        int i = new Random().nextInt(max) + 1;
        return i > 9 ? i + "" : "0" + i;
    }


    /**
     * 从String[] 数组中随机取出其中一个String字符串
     *
     * @author mingzijian
     * @param s
     * @return
     */
    public  static String randomOne(String s[]) {
        return s[new Random().nextInt(s.length - 1)];
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            BankCardBean bean = getBankCardBean();
            System.out.println("信用卡卡号:" + bean.getCardNo() + " Cvv:"+bean.getCvv()
                    + " 有效期" +bean.getValidityPeriod() + " 银行名:"+bean.getBankName() );
        }

    }
}
