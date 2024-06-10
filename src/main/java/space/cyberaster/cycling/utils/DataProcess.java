package space.cyberaster.cycling.utils;

import space.cyberaster.cycling.ginat.BicyclingRecord;
import space.cyberaster.cycling.ginat.BicyclingRecordLap;
import space.cyberaster.cycling.ginat.BicyclingRecordSecData;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DataProcess {
    public static DataProcess processor = new DataProcess();

    private DataProcess() {
    }

    public BicyclingRecord process( byte[] data) {
//        byte[] data =  dataStr.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buf = java.nio.ByteBuffer.wrap(data);

//        get_total_data(newRecord);
//        get_lap_data(newRecord);
//        get_sec_data(newRecord);
        return BicyclingRecord.getRootAsBicyclingRecord(buf);
    }

    private void get_total_data(BicyclingRecord newRecord) {
        System.out.println("开始时间: "+ newRecord.startTimestamp()); // 开始时间(时间戳/秒)
        System.out.println("结束时间: "+ newRecord.endTimestamp()); // 结束时间(时间戳/秒)
        System.out.println("运动时间: "+ newRecord.totalTimerTime()); // 运动时间(秒)
        System.out.println("里程: "+ newRecord.totalDistance()); // 里程 m
        System.out.println("卡路里: "+ newRecord.totalCalories()); // 卡路里(kcal)
        System.out.println("最高速度: "+ newRecord.maxSpeed()); // 最高速度(km*10/h)
        System.out.println("运动均速: "+ newRecord.avgSpeed());    // 运动均速(单位同上)
        System.out.println("总体均速: "+ newRecord.totalAvgSpeed());    // 总体均速(单位同上)
        System.out.println("最高踏频: "+ newRecord.maxCadence());    // 最高踏频
        System.out.println("平均踏频: "+ newRecord.avgCadence());    // 平均踏频
        System.out.println("最高心率: "+ newRecord.maxHeartRate());    // 最高心率
        System.out.println("平均心率: "+ newRecord.avgHeartRate());    // 平均心率
        System.out.println("最大功率: "+ newRecord.maxPower());    // 最大功率
        System.out.println("平均功率: "+ newRecord.avgPower());    // 平均功率
        System.out.println("累计爬升: "+ newRecord.totalAscent()); // 累计爬升(m)
        System.out.println("累计下降: "+ newRecord.totalDescent()); // 累计下降(m)
        System.out.println("最大海拔高度: "+ newRecord.maxAltitude()); // 最大海拔高度(m)
        System.out.println("最低海拔: "+ newRecord.minAltitude()); // 最低海拔
        System.out.println("************************************************************");
    }

    private void get_lap_data(BicyclingRecord newRecord) {
        for (int i = 0; true; i++) {
            try {
                BicyclingRecordLap new_lap = newRecord.bicyclingRecordLap(i);
//                // 获取每一段的数据(猜测使用手动暂停和接续骑行时会形成多段记录)
//
//                System.out.println(new_lap.m6020a());
                System.out.println("运动时间: " +new_lap.lapTimerTime());    // 运动时间(秒)
                System.out.println("里程: " +new_lap.lapDistance());   // 里程
                System.out.println("卡路里: "+ new_lap.lapCalories());   // 卡路里(kcal)
                System.out.println("最高速度: " + new_lap.maxSpeed());   // 最高速度(km*10/h)
                System.out.println("运动均速: " + new_lap.avgSpeed());   // 运动均速
                System.out.println("总体均速: " + new_lap.lapAvgSpeed());    // 总体均速
                System.out.println("最高踏频: " + new_lap.maxCadence());    // 最高踏频
                System.out.println("平均踏频: " + new_lap.avgCadence());    // 平均踏频
                System.out.println("最高心率: " + new_lap.maxHeartRate());    // 最高心率
                System.out.println("平均心率: " + new_lap.avgHeartRate());    // 平均心率
                System.out.println("最大功率: " + new_lap.maxPower());    // 最大功率
                System.out.println("平均功率: " + new_lap.avgPower());    // 平均功率
                System.out.println("累计爬升: " + new_lap.lapAscent());   // 累计爬升(m)
                System.out.println("累计下降: " + new_lap.lapDescent());   // 累计下降(m)
                System.out.println("最大海拔高度: " + new_lap.maxAltitude());   // 最大海拔高度(m)
                System.out.println("最低海拔: " + new_lap.minAltitude());   // 最低海拔
//                System.out.println(new_lap.m5969r());
//                System.out.println(new_lap.m5968s());
//                System.out.println(new_lap.m5967t());
//                System.out.println(new_lap.m5966u());
                System.out.println("--------------------------------------------------");
            } catch (Exception e) {
                System.out.println("lap: " + i);
                break;
            }
        }
    }

    public List<BicyclingRecordSecData> getSecData(BicyclingRecord newRecord) {
        List<BicyclingRecordSecData> data = new ArrayList<>();
        for (int i = 0; true; i++) {
            try {
                BicyclingRecordLap new_lap = newRecord.bicyclingRecordLap(i);
                new_lap.m6020a();
                for (int n = 0; true; n++) {
                    try {
                        BicyclingRecordSecData newSec = new_lap.bicyclingRecordSecData(n);

//                        System.out.println("时间戳: "+newSec.currentTime());    // 时间戳(s)
//                        System.out.println("类型: "+newSec.dataType());
//                        System.out.println("累计耗时: "+newSec.cumulativeTotalSecond());    // 累计耗时
//                        System.out.println("累计里程: "+newSec.cumulativeTotalDistance());    // 累计里程(m)
//                        System.out.println("累计卡路里: "+newSec.cumulativeTotalCalories());    // 累计卡路里
//                        System.out.println("速度: "+newSec.speed());    // 速度(km*10/h)
//                        System.out.println("踏频: "+newSec.cadence());    // 踏频
//                        System.out.println("心率: "+newSec.hr());    // 心率
//                        System.out.println("功率: "+newSec.power());    // 功率
//                        System.out.println("海拔: "+newSec.altitude());    // 海拔
//                        System.out.println("纬度: "+newSec.latitude());    // 纬度
//                        System.out.println("经度: "+newSec.longitude());    // 经度
//                        System.out.println("水平精度: "+newSec.horizontalAccuracy());    // 水平精度
//                        System.out.println("垂直精度: "+newSec.verticalAccuracy());    // 垂直精度
//
//
//                        long currentTime = newSec.currentTime();// 时间戳
//                        int cumulativeTotalSecond = newSec.cumulativeTotalSecond(); // 累计耗时
//                        float cumulativeTotalDistance = newSec.cumulativeTotalDistance();  // 累计里程(m)
//                        float cumulativeTotalCalories = newSec.cumulativeTotalCalories();    // 累计卡路里
//                        int speed = newSec.speed();// 速度(km*10/h)
//                        int cadence = newSec.cadence();// 踏频
//                        int hr = newSec.hr();// 心率
//                        float power = newSec.power();// 功率
//                        double altitude = newSec.altitude();// 海拔
//                        double latitude = newSec.latitude();// 纬度
//                        double longitude = newSec.longitude();// 经度
//                        int horizontalAccuracy = newSec.horizontalAccuracy();// 水平精度
//                        int verticalAccuracy = newSec.verticalAccuracy();// 垂直精度
//
//
////                        System.out.println("#######################################");
                        data.add(newSec);
                    } catch (Exception e) {
                        System.out.println("count: " + n);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("lap: " + i);
                break;
            }
        }
        return data;
    }
}
