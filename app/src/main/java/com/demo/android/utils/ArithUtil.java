package com.demo.android.utils;

import java.math.BigDecimal;

public class ArithUtil {
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    /**
     * 万单位
     */
    private static final int DEF_WAN = 10000;

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static String mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 等额本息计算公式：〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕
     *
     * @param countAmount  房价总额
     * @param paymentRatio 首付比例
     * @param totalLoan    贷款金额
     * @param loanTerm     贷款年限
     * @param rate         贷款基准利率
     * @return
     */
    public static double calBenxi(int countAmount, int paymentRatio, double totalLoan, int loanTerm, double rate) {
        double benjin = calCountAmount(countAmount, paymentRatio, totalLoan);
        //转换利率
        rate = div(rate, 100);
        //月利率=（基准利率*利率折扣）÷12
        double rateMonth = div(rate, 12);
        //计算还款月数
        int loanTermMonth = loanTerm * 12;
        //计算1+月利率平方
        double rateSqrt = Math.pow(1 + rateMonth, loanTermMonth);
        return round(div(mul(rateSqrt, mul(benjin, rateMonth)), sub(Math.pow(1 + rateMonth, loanTermMonth), 1)), 2);
    }

    /**
     * 计算等额本息利息
     * 还款总利息=贷款额*贷款月数*月利率*（1+月利率）贷款月数/【（1+月利率）还款月数 - 1】-贷款额
     *
     * @return
     */
    public static double calBenxiLixi(int countAmount, int paymentRatio, double totalLoan, int loanTerm, double rate) {
        double benjin = calCountAmount(countAmount, paymentRatio, totalLoan);
        //转换利率
        rate = div(rate, 100);
        //月利率=（基准利率*利率折扣）÷12
        double rateMonth = div(rate, 12);
        //计算还款月数
        int loanTermMonth = loanTerm * 12;
        //计算1+月利率平方
        double rateSqrt = Math.pow(1 + rateMonth, loanTermMonth);
        return round(div(mul(rateSqrt, mul(loanTermMonth, mul(benjin, rateMonth))), sub(Math.pow(1 + rateMonth, loanTermMonth), 1)) - benjin, 2);
    }

    /**
     * 计算还款总额 本金
     * @param countAmount  房价总额
     * @param paymentRatio 首付比例
     * @param totalLoan    贷款金额
     * @return
     * 设贷款额为a，月利率为i，年利率为I，还款月数为n，每月还款额为b，还款本息总和为Y
     * Y＝a×（1+i）×（1＋i）^n÷〔（1＋i）^n－1〕
     */
    public static double calCountAmount(int countAmount, int paymentRatio, double totalLoan) {

        double calTotalloan = mul(DEF_WAN, sub(countAmount, mul(countAmount, div(paymentRatio, 10))));
        double total = mul(DEF_WAN, totalLoan);
        if (total != 0 && total <= calTotalloan) {
            return total;
        }
        return mul(DEF_WAN, sub(countAmount, mul(countAmount, div(paymentRatio, 10))));
    }

    /**
     * 计算还款总额
     * @param countAmount  房价总额
     * @param paymentRatio 首付比例
     * @param totalLoan    贷款金额
     * @return
     * 设贷款额为a，月利率为i，年利率为I，还款月数为n，每月还款额为b，还款本息总和为Y
     * Y＝a×（1+i）×（1＋i）^n÷〔（1＋i）^n－1〕
     */
    public static double calCountAmount_Benxi(int countAmount, int paymentRatio, double totalLoan,int loanTerm, double rate) {
        double benjin = calCountAmount(countAmount, paymentRatio, totalLoan);
        //转换利率
        rate = div(rate, 100);
        //月利率=（基准利率*利率折扣）÷12
        double rateMonth = div(rate, 12);
        //计算还款月数
        int loanTermMonth = loanTerm * 12;
        //计算1+月利率平方
        double rateSqrt = Math.pow(1 + rateMonth, loanTermMonth);
        return round((mul((div(mul(rateSqrt, mul(benjin, rateMonth)), sub(Math.pow(1 + rateMonth, loanTermMonth), 1))),(double) loanTermMonth)),2);
    }

    /**
     * 计算贷款总额
     *
     * @return
     */
    public static double calDCountAmount(int countAmount, int paymentRatio) {
        return sub(countAmount, mul(countAmount, div(paymentRatio, 10)));
    }

    /**
     * 等额本金
     * 首月还款金额 = （贷款本金 ÷ 还款月数）+（本金 — 已归还本金累计额）×每月利率 其中＾符号表示乘方。
     *
     * @return
     */
    public static double calBenjin(int countAmount, int paymentRatio, double totalLoan, int loanTerm, double rate) {
        double benjin = calCountAmount(countAmount, paymentRatio, totalLoan);
        //计算还款月数
        int loanTermMonth = loanTerm * 12;
        //转换利率
        rate = div(rate, 100);
        //月利率=（基准利率*利率折扣）÷12
        double rateMonth = div(rate, 12);
        return round(add(div(benjin, loanTermMonth), mul(benjin, rateMonth)), 2);
    }

    /**
     * 计算等额本金利息
     * 总利息=〔(总贷款额÷还款月数+总贷款额×月利率)+总贷款额÷还款月数×(1+月利率)〕÷2×还款月数-总贷款额
     *
     * @return
     */
    public static double calBenjinLixi(int countAmount, int paymentRatio, double totalLoan, int loanTerm, double rate) {
        double benjin = calCountAmount(countAmount, paymentRatio, totalLoan);
        //转换利率
        rate = div(rate, 100);
        //月利率=（基准利率*利率折扣）÷12
        double rateMonth = div(rate, 12);
        //计算还款月数
        int loanTermMonth = loanTerm * 12;
        return round(sub(mul(div(add(add(div(benjin, loanTermMonth), mul(benjin, rateMonth)), mul(div(benjin, loanTermMonth), (1 + rateMonth))), 2), loanTermMonth), benjin), 2);
    }

    public static double calBenjinCountAmount(int countAmount, int paymentRatio, double totalLoan, int loanTerm, double rate) {
        return round(add(calBenjinLixi(countAmount, paymentRatio, totalLoan, loanTerm, rate), calCountAmount(countAmount, paymentRatio, totalLoan)), 2);
    }

    /**
     * 获取利率
     *
     * @param contect
     * @return
     */

    public static Double getRate(String contect) {
        if (contect.equals("2015年10月24日基准利率"))
            return 4.9;
        if (contect.equals("2015年10月24日利率下限(7折)"))
            return 4.9 * 0.7;
        if (contect.equals("2015年10月24日利率下限(85折)"))
            return 4.9 * 0.85;
        if (contect.equals("2015年10月24日利率下限(88折)"))
            return 4.9 * 0.88;
        if (contect.equals("2015年10月24日利率下限(9折)"))
            return 4.9 * 0.9;
        if (contect.equals("2015年10月24日利率上限(1.1倍)"))
            return 4.9 * 1.1;
        return 4.9;
    }
}
