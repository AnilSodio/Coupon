package vc.thinker.cabbage.stats;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;

import vc.thinker.cabbage.se.FeedbackConstants;
import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.dao.FeedbackDao;
import vc.thinker.cabbage.stats.bo.ItemCollector;
import vc.thinker.cabbage.stats.bo.Line;
import vc.thinker.cabbage.stats.bo.LineGroup;
import vc.thinker.cabbage.stats.bo.OrderStatsBO;
import vc.thinker.cabbage.stats.bo.StatsItem;
import vc.thinker.cabbage.stats.bo.StatsSubItem;
import vc.thinker.cabbage.stats.bo.VipStatsBO;
import vc.thinker.cabbage.stats.dao.OrderStatsDao;
import vc.thinker.cabbage.stats.dao.RegisterStatsDao;
import vc.thinker.cabbage.stats.dao.VipStatsDao;
import vc.thinker.cabbage.stats.vo.CountStatsVO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.user.bo.UserDepositLogBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.UserDepositLogDao;
import vc.thinker.cabbage.user.dao.UserMoneyRechargeDao;

/**
 * Author: Sean
 * Date: 22/8/2017
 * Time: 2:21 PM
 */
@Service
@Transactional(readOnly = false)
public class SimpleStatsService {

    private final static Logger log = LoggerFactory.getLogger(SimpleStatsService.class);
    @Autowired
    private RegisterStatsDao registerStatsDao;
    @Autowired
    private UserDepositLogDao userDepositLogDao;
    @Autowired
    private VipStatsDao vipStatsDao;

    @Autowired
    private SysSettingDao sysSettingDao;

    @Autowired
    private UserMoneyRechargeDao userMoneyRechargeDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private FeedbackDao feedbackDao;
    
    @Autowired
    private OrderStatsDao orderStatsDao;


   
 

    public List<OrderStatsBO> findAllTrips() {
        List<OrderStatsBO> list = orderStatsDao.findAll();
        return list;
    }

    public LineGroup getComplain() {
        final int days = 30;
        List<ItemCollector> items = ItemCollector.getItemsByDays(days);
        Map<Long, ItemCollector> itemsMap = new HashMap<>();
        items.forEach(item -> itemsMap.put(item.getDay(), item));


        feedbackDao.findRecentDays(days)
                .stream()
                .filter(complainStatsBO -> complainStatsBO.getCreateTime() != null)
                .collect(Collectors.groupingBy(p -> p.getCreateTime().getTime() / ItemCollector.dayMilliSeconds))
                .forEach((day, complains) -> {
                    ItemCollector itemCollector = itemsMap.get(day);
                    if (itemCollector != null) {
                        complains.forEach(item -> {
                                    if(item.getFeedType().equals(SeCommonConstants.FEEDBACK_FEED_TYPE_2)){
                                    	itemCollector.addFirst(1);
                                    }else {
                                    	itemCollector.addSecond(1);
                                    }
                        
                                    
                                }
                        );// 1 for true and 0 for false
                    }
                });

        LineGroup lineGroup = new LineGroup();
        lineGroup.setTitle("问题反馈");
        lineGroup.setViewName("complain");

        Line inRiding = new Line();
        Line other = new Line();
        inRiding.setName("行程中");
        inRiding.setStack("行程中");

        other.setName("其它");
        other.setStack("其它");
        other.setShowTop(true);

        items.forEach(itemCollector -> {
            lineGroup.addAxisLabel(itemCollector.getTitle());
            inRiding.addData(itemCollector.getFirst());
            other.addData(itemCollector.getSecond());
        });

        lineGroup.addLine(inRiding);
        lineGroup.addLine(other);

        return lineGroup;
    }

    public Map<String, Double> getVipStats() {
        AtomicDouble totalDays = new AtomicDouble(0);
        AtomicDouble totalHours = new AtomicDouble(0);
        AtomicDouble vipProfit = new AtomicDouble(0);

        List<VipStatsBO> vips = vipStatsDao.findAll();

        vips.forEach(item -> {
            if ("day".equals(item.getVipCardUnit())) {
                totalDays.addAndGet(item.getVipDays());
            } else if ("hour".equals(item.getVipCardUnit())) {
                totalHours.addAndGet(item.getVipDays());
            }
            vipProfit.addAndGet(item.getPay().doubleValue());
        });

        int userNum = registerStatsDao.findAll().size();
        int vipNum = vips.size();
        Map<String, Double> map = new HashMap<>();
        map.put("vipNum", (double) vipNum);
        map.put("vipProfit", vipProfit.get());
        map.put("vipRatio", userNum == 0 ? 0 : 100.0 * vipNum / userNum);
        map.put("vipDays", totalDays.get());
        map.put("vipHours", totalHours.get());
        return map;
    }

//    public Map<String, Double> getComplainStats() {
//        AtomicInteger totalInRiding = new AtomicInteger(0);
//        AtomicInteger totalNotInRiding = new AtomicInteger(0);
////        
////        feedbackDao.findAll()
////                .stream()
////                .collect(Collectors.groupingBy(p -> p.get))
////                .forEach((isInRiding, complain) -> {
////                    if (isInRiding) {
////                        totalInRiding.addAndGet(complain.size());
////                    } else {
////                        totalNotInRiding.addAndGet(complain.size());
////                    }
////                });
//
//        Map<String, Double> map = new HashMap<>();
//        double total = totalInRiding.get() + totalNotInRiding.get();
//        double inRidingToNot;
//        double inRidingRatio;
//        if (totalInRiding.get() + totalNotInRiding.get() == 0) {
//            inRidingToNot = 0;
//            inRidingRatio = 0;
//        } else {
//            inRidingRatio = 1.0 * totalInRiding.get() / total;
//            if (totalNotInRiding.get() == 0) {
//                inRidingToNot = 0;
//            } else {
//                inRidingToNot = 1.0 * totalInRiding.get() / totalNotInRiding.get();
//            }
//        }
//
//        map.put("totalComplain", total);
//        map.put("inRidingToNot", inRidingToNot * 100);
//        map.put("inRidingRatio", inRidingRatio * 100);
//
//        return map;
//    }

    public Map<String, Double> getProfitStats() {
        AtomicDouble total = new AtomicDouble(0);
        AtomicDouble totalVip = new AtomicDouble(0);
        AtomicDouble totalCoupon = new AtomicDouble(0);

        orderStatsDao.findAll()
                .stream()
                .filter(item -> item != null)
                .forEach(item -> {
                    if (item.getDiscountAmount() != null && item.getDiscountType() != null) {
                        if (Constant.CARD_TYPE_VIP == item.getDiscountType()) {
                            totalVip.addAndGet(item.getDiscountAmount().doubleValue());
                        } else if (Constant.CARD_TYPE_COUPON == item.getDiscountType()) {
                            totalCoupon.addAndGet(item.getDiscountAmount().doubleValue());
                        }
                    }

                    if (item.getTripAmount() != null) {
                        total.addAndGet(item.getTripAmount().doubleValue());
                    }
                });

        Map<String, Double> map = new HashMap<>();
        map.put("totalProfit", total.get());
        map.put("totalProfitReal", total.get() - totalVip.get() - totalCoupon.get());
        map.put("totalProfitVip", totalVip.get());
        map.put("totalProfitCoupon", totalCoupon.get());
        return map;
    }

    public LineGroup getAliveUsers() {
        final int days = 37;
        List<ItemCollector> items = ItemCollector.getItemsByDays(days);
        Map<Long, ItemCollector> itemsMap = new HashMap<>();
        items.forEach(item -> itemsMap.put(item.getDay(), item));

        orderStatsDao.findRecentDays(days)
                .stream()
                .filter(item -> item.getStatsDate() != null)
                .collect(Collectors.groupingBy(p -> p.getStatsDate().getTime() / ItemCollector.dayMilliSeconds))
                .forEach((day, trips) -> {
                    ItemCollector itemCollector = itemsMap.get(day);
                    if (itemCollector != null) {
                        trips.stream()
                                .filter(p -> p.getUid() != null)
                                .collect(Collectors.groupingBy(p -> p.getUid()))
                                .forEach((uid, inner_trips) -> itemCollector.addFirst(1));
                    }
                });


        LineGroup lineGroup = new LineGroup();
        lineGroup.setTitle("活跃用户");
        lineGroup.setViewName("aliveUser");

        Line sevenDaysAgoAlive = new Line();
        sevenDaysAgoAlive.setName("7天前");
        sevenDaysAgoAlive.setStack("7天前活跃");

        Line currentAlive = new Line();
        currentAlive.setName("当前");
        currentAlive.setStack("当前");
        currentAlive.setShowTop(true);

        int aMonth = days - 7;
        for (int i = 0; i < aMonth; i++) {
            lineGroup.addAxisLabel(items.get(i + 7).getTitle());
            sevenDaysAgoAlive.addData(items.get(i).getFirst());
            currentAlive.addData(items.get(i + 7).getFirst());
        }


        lineGroup.addLine(sevenDaysAgoAlive);
        lineGroup.addLine(currentAlive);
        return lineGroup;
    }

    public Map<String, Double> getDepositStats() {
        AtomicDouble totalCharge = new AtomicDouble(0.0);
        AtomicDouble totalWithdraw = new AtomicDouble(0.0);
        AtomicDouble totalUser = new AtomicDouble(0.0);
        AtomicDouble yesterdayCharge = new AtomicDouble(0.0);
        AtomicDouble todayCharge = new AtomicDouble(0.0);
        AtomicDouble yesterdayWithdraw = new AtomicDouble(0.0);
        AtomicDouble todayWithdraw = new AtomicDouble(0.0);

        Calendar calendar = Calendar.getInstance();
        long todayIndex = calendar.getTime().getTime() / ItemCollector.dayMilliSeconds;
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long yesterdayIndex = calendar.getTime().getTime() / ItemCollector.dayMilliSeconds;

        List<UserDepositLogBO> allDeposit = userDepositLogDao.findAll();

        allDeposit.stream()
                .filter(p -> p.getUid() != null)
                .collect(Collectors.groupingBy(p -> p.getUid()))
                .forEach((uid, depos) -> {
                    totalUser.addAndGet(1);
                });


        allDeposit.stream()
                .filter(p -> p.getCreateTime() != null)
                .collect(Collectors.groupingBy(p -> p.getCreateTime().getTime() / ItemCollector.dayMilliSeconds))
                .forEach((day, depos) -> {
                    depos.forEach(depo -> {
                        if (Constant.CHARGE.equals(depo.getType())) {
                            totalCharge.addAndGet(depo.getAmount().doubleValue());
                        } else if (Constant.WITHDRAW.equals(depo.getType())) {
                            totalWithdraw.addAndGet(depo.getAmount().doubleValue());
                        }

                        if (yesterdayIndex == day) {
                            if (Constant.CHARGE.equals(depo.getType())) {
                                yesterdayCharge.addAndGet(depo.getAmount().doubleValue());
                            } else if (Constant.WITHDRAW.equals(depo.getType())) {
                                yesterdayWithdraw.addAndGet(depo.getAmount().doubleValue());
                            }
                        }

                        if (todayIndex == day) {
                            if (Constant.CHARGE.equals(depo.getType())) {
                                todayCharge.addAndGet(depo.getAmount().doubleValue());
                            } else if (Constant.WITHDRAW.equals(depo.getType())) {
                                todayWithdraw.addAndGet(depo.getAmount().doubleValue());
                            }
                        }
                    });
                });

        Map<String, Double> map = new HashMap<>();
        map.put("consumeTotal", totalCharge.get() - totalWithdraw.get());
        map.put("consumeUserTotal", totalUser.get());
        map.put("yesterdayCharge", yesterdayCharge.get());
        map.put("yesterdayWithdraw", yesterdayWithdraw.get());
        map.put("todayCharge", todayCharge.get());
        map.put("todayWithdraw", todayWithdraw.get());
        return map;
    }

    public Map<String, Double> getBikeUsageTotal() {
        AtomicInteger total = new AtomicInteger(0);
        AtomicInteger totalDuration = new AtomicInteger(0);
        AtomicDouble totalDistance = new AtomicDouble(0.0);
        AtomicInteger totalUseTime = new AtomicInteger(0);
        orderStatsDao.findAll()
                .stream()
                .collect(Collectors.groupingBy(p -> p.getUid()))
                .forEach((uid, trips) -> {
                    total.addAndGet(1);
                    trips.forEach(trip -> {
                        totalUseTime.addAndGet(1);
                        totalDuration.addAndGet(trip.getDuration());
                        totalDistance.addAndGet(trip.getDistance().doubleValue());
                    });
                });

        Map<String, Double> map = new HashMap<>();
        map.put("total", (double) total.get());
        map.put("averageUseTime", total.get() == 0 ? 0.0 : 1.0 * totalUseTime.get() / total.get());
        map.put("averageDuration", total.get() == 0 ? 0.0 : 1.0 * totalDuration.get() / total.get());
        map.put("averageDistance", total.get() == 0 ? 0.0 : 1.0 * totalDistance.get() / total.get());

        return map;
    }

    public LineGroup getBikeUsage() {
        final int days = 30;
        List<ItemCollector> items = ItemCollector.getItemsByDays(days);
        Map<Long, ItemCollector> itemsMap = new HashMap<>();
        items.forEach(item -> itemsMap.put(item.getDay(), item));

        orderStatsDao.findRecentDays(days)
                .stream()
                .filter(item -> item.getStatsDate() != null)
                .collect(Collectors.groupingBy(p -> p.getStatsDate().getTime() / ItemCollector.dayMilliSeconds))
                .forEach((day, trips) -> {
                    ItemCollector itemCollector = itemsMap.get(day);
                    if (itemCollector != null) {
                        trips.stream()
                                .filter(p -> p.getUid() != null)
                                .forEach(item -> {
                                    itemCollector.addFirst(item.getDistance().doubleValue());
                                    itemCollector.addSecond(item.getDuration());
                                });
                    }
                });

        LineGroup lineGroup = new LineGroup();
        lineGroup.setTitle("车辆统计");
        lineGroup.setViewName("bikeUsage");

        Line rideDistance = new Line();
        rideDistance.setName("骑行总距离(公里)");
        rideDistance.setStack("骑行距离");

        Line rideTime = new Line();
        rideTime.setName("骑行总时间(分钟)");
        rideTime.setStack("骑行时间");
        rideTime.setShowTop(true);

        items.forEach(item -> {
            lineGroup.addAxisLabel(item.getTitle());
            rideDistance.addData(item.getFirst());
            rideTime.addData(item.getSecond());
        });

        lineGroup.addLine(rideTime);
        lineGroup.addLine(rideDistance);
        return lineGroup;
    }

    public Map<String, Integer> getTripStatsTotal() {
        Calendar calendar = Calendar.getInstance();
        final long todayIndex = calendar.getTime().getTime() / ItemCollector.dayMilliSeconds;
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        final long yesterdayIndex = calendar.getTime().getTime() / ItemCollector.dayMilliSeconds;

        AtomicInteger total = new AtomicInteger(0);
        AtomicInteger today = new AtomicInteger(0);
        AtomicInteger yesterday = new AtomicInteger(0);
        orderStatsDao.findAll()
                .forEach(item -> {

                    long day = item.getStatsDate().getTime() / ItemCollector.dayMilliSeconds;

                    if (todayIndex == day) {
                        today.addAndGet(1);
                    }

                    if (yesterdayIndex == day) {
                        yesterday.addAndGet(1);
                    }

                    total.addAndGet(1);
                });

        Map<String, Integer> map = new HashMap<>();

        map.put("totalRide", total.get());
        map.put("todayRide", today.get());
        map.put("yesterdayRide", yesterday.get());
        return map;
    }

    public List<StatsItem> getTripStats() {
        List<OrderStatsBO> list = orderStatsDao.findAll();
        Map<String, Integer> map = new HashMap<>();
        map.put(Age_0_20, 0);
        map.put(Age_20_30, 0);
        map.put(Age_30_40, 0);
        map.put(Age_40, 0);
        map.put(Age_unknown, 0);
        map.put(Gender_male, 0);
        map.put(Gender_female, 0);
        map.put(Gender_unknown, 0);
        map.put(Distance_0_1, 0);
        map.put(Distance_1_3, 0);
        map.put(Distance_3, 0);
        map.put(Time_0_10, 0);
        map.put(Time_10_30, 0);
        map.put(Time_30_60, 0);
        map.put(Time_60, 0);

        map.put(Charged, 0);
        map.put(Charged_Not, 0);
        map.put(Vip_Num, 0);
        map.put(Not_Vip_Num, 0);
        map.put(Feedback_Index, 0);
        map.put(Feedback_Trip, 0);
        map.put(Feedback_Done, 0);

        map.put(Pay_Balance, 0);
        map.put(Pay_Cash, 0);
        map.put(Pay_Vip, 0);

        list.stream()
                .flatMap(tripStatsBO -> {
                    List<String> innerList = Lists.newArrayList();
                    //分别解析出每个统计条目。在forEach步骤数每个统计条目出现的次数。
                    innerList.add(parseTime(tripStatsBO.getDuration()));
                    innerList.add(parseDistance(tripStatsBO.getDistance()));
                    return innerList.stream();
                })
                .forEach(key -> {
                    Integer total = map.get(key);
                    if (total == null) {
                        total = 0;
                    }
                    total++;
                    map.put(key, total);
                });


        registerStatsDao.getAllActiveUser().stream()
                .flatMap(registerStatsBO -> {
                    List<String> innerList = Lists.newArrayList();
                    innerList.add(parseAge(registerStatsBO.getAge()));
                    innerList.add(parseGender(registerStatsBO.getSex()));
                    return innerList.stream();
                })
                .forEach(key -> {
                    Integer total = map.get(key);
                    if (total == null) {
                        total = 0;
                    }
                    total++;
                    map.put(key, total);
                });


        List<StatsItem> stats = new ArrayList<>();
        StatsItem statsItem;

//        statsItem = new StatsItem();
//        statsItem.setTitle("用户行程骑行距离占比图");
//        statsItem.setSubTitle("行程距离");
//        if (map.get(Distance_0_1) + map.get(Distance_1_3) + map.get(Distance_3) > 0) {
//            statsItem.addItem(new StatsSubItem(Distance_0_1, map.get(Distance_0_1)));
//            statsItem.addItem(new StatsSubItem(Distance_1_3, map.get(Distance_1_3)));
//            statsItem.addItem(new StatsSubItem(Distance_3, map.get(Distance_3)));
//        } else {
//            statsItem.addItem(new StatsSubItem("暂无骑行距离", 1));
//        }
//        stats.add(statsItem);

//        statsItem = new StatsItem();
//        statsItem.setTitle("用户行程骑行时间占比图");
//        statsItem.setSubTitle("骑行时间");
//        if (map.get(Time_0_10) + map.get(Time_10_30) + map.get(Time_30_60) + map.get(Time_60) > 0) {
//            statsItem.addItem(new StatsSubItem(Time_0_10, map.get(Time_0_10)));
//            statsItem.addItem(new StatsSubItem(Time_10_30, map.get(Time_10_30)));
//            statsItem.addItem(new StatsSubItem(Time_30_60, map.get(Time_30_60)));
//            statsItem.addItem(new StatsSubItem(Time_60, map.get(Time_60)));
//        } else {
//            statsItem.addItem(new StatsSubItem("暂无骑行时间", 1));
//        }
//        stats.add(statsItem);

//        statsItem = new StatsItem();
//        statsItem.setTitle("User gender figure");
//        statsItem.setSubTitle("gender");
//        if (map.get(Gender_male) + map.get(Gender_female) + map.get(Gender_unknown) > 0) {
//            statsItem.addItem(new StatsSubItem(Gender_male, map.get(Gender_male)));
//            statsItem.addItem(new StatsSubItem(Gender_female, map.get(Gender_female)));
//            statsItem.addItem(new StatsSubItem(Unknown, map.get(Gender_unknown)));
//        } else {
//            statsItem.addItem(new StatsSubItem("No gender statistics", 1));
//        }
//
//        stats.add(statsItem);

//        statsItem = new StatsItem();
//        statsItem.setTitle("User age figure");
//        statsItem.setSubTitle("age");
//        if (map.get(Age_0_20) + map.get(Age_20_30) + map.get(Age_30_40) + map.get(Age_40) + map.get(Age_unknown) > 0) {
//            statsItem.addItem(new StatsSubItem(Age_0_20, map.get(Age_0_20)));
//            statsItem.addItem(new StatsSubItem(Age_20_30, map.get(Age_20_30)));
//            statsItem.addItem(new StatsSubItem(Age_30_40, map.get(Age_30_40)));
//            statsItem.addItem(new StatsSubItem(Age_40, map.get(Age_40)));
//            statsItem.addItem(new StatsSubItem(Unknown, map.get(Age_unknown)));
//        } else {
//            statsItem.addItem(new StatsSubItem("No age statistics", 1));
//        }
//
//        stats.add(statsItem);

//        statsItem = new StatsItem();
//        statsItem.setTitle("User pre-charge figure accounted for");
//        statsItem.setSubTitle("Pre-recharge");
//        int charged = userMoneyRechargeDao.chargedUserNum();
        long totalMember = memberDao.count();
//
//        if (totalMember > 0) {
//            statsItem.addItem(new StatsSubItem(Charged, charged));
//            statsItem.addItem(new StatsSubItem(Charged_Not, (int) (totalMember - charged)));
//        } else {
//            statsItem.addItem(new StatsSubItem("No user", 1));
//        }
//        stats.add(statsItem);

        statsItem = new StatsItem();
        statsItem.setTitle("Member proportion");
        statsItem.setSubTitle("member");
        long vipNum = vipStatsDao.totalVips();
        if (totalMember > 0) {
            statsItem.addItem(new StatsSubItem(Vip_Num, (int) vipNum));
            statsItem.addItem(new StatsSubItem(Not_Vip_Num, (int) (totalMember - vipNum)));
        } else {
            statsItem.addItem(new StatsSubItem("暂无用户", 1));
        }
        stats.add(statsItem);

        statsItem = new StatsItem();
        statsItem.setTitle("Feedback proportion");
        statsItem.setSubTitle("problem");
        List<FeedbackBO> feedbackCountVOS = feedbackDao.groupByFeedType();
        feedbackCountVOS.forEach(item -> {
            if (FeedbackConstants.FEED_TYPE_1.equals(item.getFeedType())) {
                map.put(Feedback_Index, item.getNum());
            } else if (FeedbackConstants.FEED_TYPE_2.equals(item.getFeedType())) {
                map.put(Feedback_Trip, item.getNum());
            } else if (FeedbackConstants.FEED_TYPE_3.equals(item.getFeedType())) {
                map.put(Feedback_Done, item.getNum());
            }
        });

        if (map.get(Feedback_Index) + map.get(Feedback_Trip) + map.get(Feedback_Done) > 0) {
            statsItem.addItem(new StatsSubItem(Feedback_Index, map.get(Feedback_Index)));
            statsItem.addItem(new StatsSubItem(Feedback_Trip, map.get(Feedback_Trip)));
            statsItem.addItem(new StatsSubItem(Feedback_Done, map.get(Feedback_Done)));
        } else {
            statsItem.addItem(new StatsSubItem("No user feedback", 1));
        }

        stats.add(statsItem);

        statsItem = new StatsItem();
        statsItem.setTitle("Platform revenue proportion");
        statsItem.setSubTitle("income");

        List<OrderStatsBO> payCount = orderStatsDao.groupByPayType();
        payCount.stream()
                .filter(item -> item.getPayType() != null)
                .filter(item -> item.getActualConsume() != null)
                .forEach(item -> {
                    if (OrderConstants.PAY_TYPE_CASH.equals(item.getPayType())) {
                        map.put(Pay_Cash, (int) item.getActualConsume().doubleValue());
                    }
                });

        Double totalRecharge = userMoneyRechargeDao.totalCharged();
        if (totalRecharge != null) {
            map.put(Pay_Balance, (int) totalRecharge.doubleValue());
        }

        Double vipRecharge = vipStatsDao.getTotalVipRecharge();
        if (vipRecharge != null) {
            map.put(Pay_Vip, (int) vipRecharge.doubleValue());
        }

        if (map.get(Pay_Balance) + map.get(Pay_Vip) + map.get(Pay_Cash) > 0) {
            statsItem.addItem(new StatsSubItem(Pay_Balance, map.get(Pay_Balance)));
            statsItem.addItem(new StatsSubItem(Pay_Vip, map.get(Pay_Vip)));
            statsItem.addItem(new StatsSubItem(Pay_Cash, map.get(Pay_Cash)));
        } else {
            statsItem.addItem(new StatsSubItem("No income", 1));
        }

        stats.add(statsItem);

        return stats;

    }

 
    public Integer getTotalUserNumber() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        registerStatsDao.findAll()
                .stream()
                .filter(p -> p.getUid() != null)
                .collect(Collectors.groupingBy(p -> p.getUid()))
                .forEach((uid, reg) -> {
                    atomicInteger.addAndGet(1);
                });
        return atomicInteger.get();
    }

    public Integer getActiveUserByDay(Calendar calendar) {
        List<OrderStatsBO> tripStatsBOS = orderStatsDao.findByDate(calendar);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        tripStatsBOS.stream()
                .filter(p -> p.getUid() != null)
                .collect(Collectors.groupingBy(p -> p.getUid()))
                .forEach((uid, trips) -> {
                    atomicInteger.addAndGet(1);
                });

        return atomicInteger.get();
    }

    private String parseGender(Integer gender) {
        if (gender == null) return Gender_unknown;

        return gender == 1 ? Gender_male : Gender_female;
    }

    private String parseTime(Integer time) {
        if (time == null) return Unknown;

        String interval = Time_60;
        if (time < 10) {
            return Time_0_10;
        } else if (time < 30) {
            return Time_10_30;
        } else if (time < 60) {
            return Time_30_60;
        }
        return interval;
    }

    private String parseDistance(BigDecimal _distance) {
        if (_distance == null) return Unknown;

        String interval = Distance_3;
        double distance = _distance.doubleValue();
        if (distance < 1000.0) {
            return Distance_0_1;
        } else if (distance < 3000.0) {
            return Distance_1_3;
        }
        return interval;
    }

    private String parseAge(Integer age) {
        if (age == null) return Age_unknown;

        String interval = Age_40;
        if (age < 20) {
            return Age_0_20;
        } else if (age < 30) {
            return Age_20_30;
        } else if (age < 40) {
            return Age_30_40;
        }
        return interval;
    }

    private class Stats {
        public String time;
        public String distance;
        public String age;
        public String gender;

        public Stats(String time, String distance, String age, String gender) {
            this.time = time;
            this.age = age;
            this.gender = gender;
            this.distance = distance;
        }
    }


    private final String Unknown = "unknown";
    private final String Age_0_20 = "1-20year old";
    private final String Age_20_30 = "20-30year old";
    private final String Age_30_40 = "30-40year old";
    private final String Age_40 = "40year old以上";
    private final String Age_unknown = "年龄未知";

    private final String Gender_male = "male";
    private final String Gender_female = "Female";
    private final String Gender_unknown = "unknown";

    private final String Distance_0_1 = "0-1Km";
    private final String Distance_1_3 = "1-3Km";
    private final String Distance_3 = "3Km以上";

    private final String Time_0_10 = "0-10minute";
    private final String Time_10_30 = "10-30minute";
    private final String Time_30_60 = "30-60minute";
    private final String Time_60 = "60minute以上";

    private final String Charged = "Recharged";
    private final String Charged_Not = "Not refilled";

    private final String Vip_Num = "member";
    private final String Not_Vip_Num = "Non-members";

    private final String Feedback_Index = "Home";
    private final String Feedback_Trip = "Using";
    private final String Feedback_Done = "completed";

    private final String Pay_Cash = "Order payment";
    private final String Pay_Vip = "membership card payment";
    private final String Pay_Balance = "Balance payment";

    /**
     * 用户反馈问题事件
     */
    public static class UserComplainEvent {
        //用户id
        private Long uid;
        //是否是在行程中反馈的问题
        private Boolean isInRiding;

        private Date complainDate;

        public Boolean getInRiding() {
            return isInRiding;
        }

        public void setInRiding(Boolean inRiding) {
            isInRiding = inRiding;
        }

        public Date getComplainDate() {
            return complainDate == null ? new Date() : complainDate;
        }

        public void setComplainDate(Date complainDate) {
            this.complainDate = complainDate;
        }

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }
    }

    /**
     * 车辆投放事件
     */
    public static class BikeLaunchEvent {
        //车牌号或者车辆ID都可以
        private Long bicycleId;
        //车辆投放时间
        private Date launchDate;

        public Long getBicycleId() {
            return bicycleId;
        }

        public void setBicycleId(Long bicycleId) {
            this.bicycleId = bicycleId;
        }

        public Date getLaunchDate() {
            return launchDate == null ? new Date() : launchDate;
        }

        public void setLaunchDate(Date launchDate) {
            this.launchDate = launchDate;
        }
    }

    public static class BatteryLaunchEvent {
        private Long batteryId;
        private Date launchDate;

        public Long getBatteryId() {
            return batteryId;
        }

        public void setBatteryId(Long batteryId) {
            this.batteryId = batteryId;
        }

        public Date getLaunchDate() {
            return launchDate == null ? new Date() : launchDate;
        }

        public void setLaunchDate(Date launchDate) {
            this.launchDate = launchDate;
        }
    }
    
    public static class FeedbackMessageEvent {
    	
    	private Long feedbackId;

		public Long getFeedbackId() {
			return feedbackId;
		}

		public void setFeedbackId(Long feedbackId) {
			this.feedbackId = feedbackId;
		}
    }
}

class Constant {
    public static final Integer CARD_TYPE_VIP = 2;
    public static final Integer CARD_TYPE_COUPON = 3;

    public static final String CHARGE = "1";//充值成功
    public static final String WITHDRAW = "3";//退款成功
}
