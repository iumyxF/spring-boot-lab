package com.example.practice.leetcode.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fzy
 * @description:
 * @date 2024/11/30 15:05
 */
public class BestUseCoupon {

    /**
     * [123] -3000 -1400 -100 >>> -4500
     * [132] -3000 -100 -1380 >>> -4480
     * [213] -2000 -3000 -100 >>> -5100
     * [231] -2000 -100 -3000 >>> -5100
     * [312] -100 -3000 -1380 >>> -4480
     * [321] -100 -1980 -3000 >>> -5080
     * <p>
     * 扩展：
     * 1. 如果优惠券每种类型有使用限制
     * a. 在dfs()方法中再增加一个数组，标识每种券的使用数量是否达到上限
     */
    public static void main(String[] args) {
        // 注意金额单位是分 100元的订单
        Order order = new Order(1001, 3, 10000);
        ArrayList<Coupon> coupons = new ArrayList<>();
        // 30元的抵扣券
        coupons.add(new Coupon(1, 1, 100, 3000));
        coupons.add(new Coupon(2, 2, 100, 80));
        coupons.add(new Coupon(3, 1, 100, 100));
        // 暂时不考虑时长券
        //coupons.add(new Coupon(4, 3, 10, 30));
        CouponHandler handler = new CouponHandler();
        handler.handle(order, coupons);
    }
}

class CouponHandler {

    private final CouponHandlerFactory couponHandlerFactory;

    private final List<CouponCalculationResultBo> couponUsedCollection;


    public CouponHandler() {
        couponHandlerFactory = new CouponHandlerFactory();
        couponUsedCollection = new ArrayList<>();
    }

    /**
     * 要求控制台输出
     * 1. 能扣减的最大金额
     * 2. 使用的优惠券顺序
     *
     * @param order         订单信息
     * @param useCouponList 用户所持有的优惠券信息
     */
    public void handle(Order order, List<Coupon> useCouponList) {
        boolean[] used = new boolean[useCouponList.size()];
        dfs(useCouponList, used, order.getAmount(), new ArrayList<>(useCouponList.size()), 0, 0, order.getOccupation());
        // 输出全部解
        consoleOutput(couponUsedCollection);
        // 输出最优解
        System.out.println("###");
        consoleOutputOptimalSolution(order.getAmount(), couponUsedCollection);
    }

    public void dfs(List<Coupon> useCouponList, boolean[] used, Integer amount, List<Coupon> path, int index, int deduct, int occupation) {
        if (amount <= 0 || index == useCouponList.size()) {
            couponUsedCollection.add(new CouponCalculationResultBo(new ArrayList<>(path), deduct));
            return;
        }
        for (int i = 0; i < useCouponList.size(); i++) {
            if (used[i]) {
                continue;
            }
            index++;
            Integer deduction = couponHandlerFactory.calculateAmountOfDeduction(new CouponCalculationRequestBo(amount, occupation), useCouponList.get(i));
            if (deduction == 0) {
                continue;
            }
            used[i] = true;
            path.add(useCouponList.get(i));
            deduct += deduction;
            amount -= deduction;
            dfs(useCouponList, used, amount, path, index, deduct, occupation);
            used[i] = false;
            path.remove(path.size() - 1);
            amount += deduction;
            deduct -= deduction;
            index--;
        }
    }

    private void consoleOutput(List<CouponCalculationResultBo> couponUsedCollection) {
        for (int i = 0; i < couponUsedCollection.size(); i++) {
            CouponCalculationResultBo bo = couponUsedCollection.get(i);
            System.out.println("优惠券使用顺序: " + bo.getCouponUsedOrderList().stream().map(Coupon::getId).collect(Collectors.toList())
                    + " ,能抵扣的金额: " + bo.getDeduct());
        }
    }

    /**
     * 如果金额能抵扣完，最优解为使用最少优惠券的方案
     * 如果金额不能抵扣完，最优解为能抵扣的最大金额
     *
     * @param amount
     * @param couponUsedCollection
     */
    private void consoleOutputOptimalSolution(Integer amount, List<CouponCalculationResultBo> couponUsedCollection) {
        // 查询能抵扣完的方案
        ArrayList<CouponCalculationResultBo> fullCancellation = new ArrayList<>(couponUsedCollection.size());
        for (CouponCalculationResultBo calculationBo : couponUsedCollection) {
            if (calculationBo.getDeduct() >= amount) {
                fullCancellation.add(calculationBo);
            }
        }
        int optimalIndex = 0;
        // 金额能抵扣完，最优解为使用最少优惠券的方案
        if (fullCancellation.size() > 0) {
            int usedLen = Integer.MAX_VALUE;
            for (int i = 0; i < fullCancellation.size(); i++) {
                CouponCalculationResultBo bo = fullCancellation.get(i);
                if (bo.getCouponUsedOrderList().size() < usedLen) {
                    usedLen = bo.getCouponUsedOrderList().size();
                    optimalIndex = i;
                }
            }
            System.out.println("最优解方案: " + fullCancellation.get(optimalIndex).getCouponUsedOrderList().stream().map(Coupon::getId).collect(Collectors.toList())
                    + " ,能抵扣的金额: " + fullCancellation.get(optimalIndex).getDeduct());
        } else {
            int maxDeduct = Integer.MIN_VALUE;
            for (int i = 0; i < couponUsedCollection.size(); i++) {
                CouponCalculationResultBo bo = couponUsedCollection.get(i);
                if (bo.getDeduct() > maxDeduct) {
                    maxDeduct = bo.getDeduct();
                    optimalIndex = i;
                }
            }
            System.out.println("最优解方案: " + couponUsedCollection.get(optimalIndex).getCouponUsedOrderList().stream().map(Coupon::getId).collect(Collectors.toList())
                    + " ,能抵扣的金额: " + couponUsedCollection.get(optimalIndex).getDeduct());
        }
    }
}

class CouponHandlerFactory {

    private static final Map<Integer, ICouponHandler> HANDLER_MAP = new HashMap<>();

    static {
        HANDLER_MAP.put(1, new FixedCouponHandler());
        HANDLER_MAP.put(2, new DiscountCouponHandler());
        HANDLER_MAP.put(3, new TimeCouponHandler());
    }

    /**
     * 计算优惠券能抵扣的金额
     *
     * @param requestBo 订单计算请求体
     * @param coupon    优惠券
     * @return 抵扣金额
     */
    public Integer calculateAmountOfDeduction(CouponCalculationRequestBo requestBo, Coupon coupon) {
        ICouponHandler handler = HANDLER_MAP.get(coupon.getType());
        if (null == handler || requestBo.getAmount() < coupon.getThreshold()) {
            return 0;
        }
        return handler.compute(requestBo, coupon);
    }
}


interface ICouponHandler {

    /**
     * 计算优惠券能抵扣多少钱
     *
     * @param requestBo 计算请求体
     * @param coupon    优惠券
     * @return 能抵扣的金额
     */
    Integer compute(CouponCalculationRequestBo requestBo, Coupon coupon);
}

class FixedCouponHandler implements ICouponHandler {

    @Override
    public Integer compute(CouponCalculationRequestBo requestBo, Coupon coupon) {
        return coupon.getValue();
    }
}

class DiscountCouponHandler implements ICouponHandler {

    @Override
    public Integer compute(CouponCalculationRequestBo requestBo, Coupon coupon) {
        Integer amount = requestBo.getAmount();
        return Math.toIntExact(Math.round(amount - amount * coupon.getValue() / 100.00));
    }
}

class TimeCouponHandler implements ICouponHandler {

    /**
     * 每分钟 100 分 （每分钟 1 元）
     */
    private static final int roomPricePerMinute = 100;

    @Override
    public Integer compute(CouponCalculationRequestBo requestBo, Coupon coupon) {
        // 如果订单占用时间 比 优惠券扣减时间长，则有效时间是 优惠券的扣减时间，反之则是订单的占用时间
        int effectiveTime = requestBo.getOccupation() >= coupon.getValue() ?
                coupon.getValue() : requestBo.getOccupation();
        return effectiveTime * roomPricePerMinute;
    }
}

class Coupon {

    /**
     * 优惠券id
     */
    private Integer id;

    /**
     * 优惠券类型，针对预约会议室场景
     * 1. 固定金额扣减券 如满100-20
     * 2，折扣券 如打8折
     * 3. 时长抵扣券 如能抵扣预约时间的1小时
     */
    private Integer type;

    /**
     * 使用门槛
     */
    private Integer threshold;

    /**
     * type = 1 时 value 范围 0 ~ Integer.MAX_VALUE
     * type = 2 时 value 范围 0 ~ 100, 88代表 8.8折
     * type = 3 时 value 范围 0 ~ Integer.MAX_VALUE 单位分钟
     */
    private Integer value;

    public Coupon(Integer id, Integer type, Integer threshold, Integer value) {
        this.id = id;
        this.type = type;
        this.threshold = threshold;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public Integer getValue() {
        return value;
    }
}

class Order {

    private Integer id;

    /**
     * 订单占用的会议时长
     */
    private Integer occupation;

    /**
     * 订单金额 单位分
     */
    private Integer amount;

    public Order(Integer id, Integer occupation, Integer amount) {
        this.id = id;
        this.occupation = occupation;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public Integer getAmount() {
        return amount;
    }
}

class CouponCalculationResultBo {

    /**
     * 优惠券使用的顺序
     */
    private List<Coupon> couponUsedOrderList;

    /**
     * 该顺序能抵扣的金额
     */
    private Integer deduct;

    public CouponCalculationResultBo(List<Coupon> couponUsedOrderList, Integer deduct) {
        this.couponUsedOrderList = couponUsedOrderList;
        this.deduct = deduct;
    }

    public List<Coupon> getCouponUsedOrderList() {
        return couponUsedOrderList;
    }

    public Integer getDeduct() {
        return deduct;
    }
}

class CouponCalculationRequestBo {

    /**
     * 订单占用的会议时长 单位分钟
     */
    private Integer occupation;

    /**
     * 订单金额 单位分
     */
    private Integer amount;

    public CouponCalculationRequestBo(Integer occupation, Integer amount) {
        this.occupation = occupation;
        this.amount = amount;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public Integer getAmount() {
        return amount;
    }
}