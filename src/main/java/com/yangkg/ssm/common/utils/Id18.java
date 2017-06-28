package com.yangkg.ssm.common.utils;

;
;

/**
 * (一)18身份证号码的结构

 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。

 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位校验码。

 1、地址码

 表示编码对象常住户口所在县(市、旗、区)的行政区域划分代码，按GB/T2260的规定执行。

 2、出生日期码

 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。

 3、顺序码

 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。

 4、校验码计算步骤

 (1)十七位数字本体码加权求和公式

 S = Sum(Ai * Wi), i = 0, … , 16 ，先对前 17 位数字的权求和
 Ai：表示第i位置上的身份证号码数字值(0~9)
 Wi：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 （表示第 i 位置上的加权因子）
 (2)计算模

 Y = mod(S, 11)

 (3)根据模，查找得到对应的校验码

 Y: 0 1 2 3 4 5 6 7 8 9 10
 校验码: 1 0 X 9 8 7 6 5 4 3 2

 (三)说明

 1.程序可以根据已有的17位数字本体码，获取对应的验证码。

 2.该程序可以剔除验证码不正确的身份证号码。

 3.15位的身份证出生年份采用年份后2位，没有最后1位校验码。

 4.完整的身份证18位，最后一位校验位可能是非数字。我们的一个项目，数据库保存前17位数字，这样对应一些SQL语句（比如inner join）有加速作用的！！！

 * 根据17位数字本体码获取最后一位校验码程序实例
 *
 * @author：Kyle.yangkg
 * @create：2017-06-22 上午 10:34
 * ©copyright：www.aisino.com
 */
public class Id18 {
    int[] weight={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};    //十七位数字本体码权重
    char[] validate={ '1','0','X','9','8','7','6','5','4','3','2'};    //mod11,对应校验码字符值

    public char getValidateCode(String id17){
        int sum = 0;
        int mode = 0;
        for(int i = 0; i < id17.length(); i++){
            sum=sum+Integer.parseInt(String.valueOf(id17.charAt(i)))*weight[i];
        }
        mode = sum % 11;
        return validate[mode];
    }

    public static void main(String[] args){
        Id18 test= new Id18();
        System.out.println("该身份证验证码："+test.getValidateCode("53038119921128331"));    //该身份证校验码：1

        //另外补充一个校验身份证的pattern，先用pattern校验，再用这个做 这里日期只支持19和20开头的
        String id18Regex = "[1-9][0-7]\\d{4}((((19|20)\\d{2})(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(((19|20)\\d{2})(0[13578]|1[02])31)|(((19|20)\\d{2})02(0[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))0229))\\d{3}(\\d|X|x)";

        System.out.println("530381199211283311".matches(id18Regex));
    }
}
