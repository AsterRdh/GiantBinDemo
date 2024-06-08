package space.cyberaster.cycling;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import space.cyberaster.cycling.ginat.BicyclingRecord;
import space.cyberaster.cycling.ginat.BicyclingRecordLap;
import space.cyberaster.cycling.ginat.BicyclingRecordSecData;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{

    private void process() {
        Path path = Paths.get("E:\\demo\\ganit\\GinatBinDemo\\src\\main\\resources\\bin");
        try {
            byte[] data = Files.readAllBytes(path);
            ByteBuffer buf = java.nio.ByteBuffer.wrap(data);
            BicyclingRecord new_rcord = BicyclingRecord.getRootAsBicyclingRecord(buf);

            get_total_data(new_rcord);
            get_lap_data(new_rcord);
            get_sec_data(new_rcord);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private void get_total_data(BicyclingRecord new_rcord) {
        System.out.println("开始时间: "+new_rcord.startTimestamp()); // 开始时间(时间戳/秒)
        System.out.println("结束时间: "+new_rcord.endTimestamp()); // 结束时间(时间戳/秒)
        System.out.println("运动时间: "+new_rcord.totalTimerTime()); // 运动时间(秒)
        System.out.println("里程: "+new_rcord.totalDistance()); // 里程 m
        System.out.println("卡路里: "+new_rcord.totalCalories()); // 卡路里(kcal)
        System.out.println("最高速度: "+new_rcord.maxSpeed()); // 最高速度(km*10/h)
        System.out.println("运动均速: "+new_rcord.avgSpeed());    // 运动均速(单位同上)
        System.out.println("总体均速: "+new_rcord.totalAvgSpeed());    // 总体均速(单位同上)
        System.out.println("最高踏频: "+new_rcord.maxCadence());    // 最高踏频
        System.out.println("平均踏频: "+new_rcord.avgCadence());    // 平均踏频
        System.out.println("最高心率: "+new_rcord.maxHeartRate());    // 最高心率
        System.out.println("平均心率: "+new_rcord.avgHeartRate());    // 平均心率
        System.out.println("最大功率: "+new_rcord.maxPower());    // 最大功率
        System.out.println("平均功率: "+new_rcord.avgPower());    // 平均功率
        System.out.println("累计爬升: "+new_rcord.totalAscent()); // 累计爬升(m)
        System.out.println("累计下降: "+new_rcord.totalDescent()); // 累计下降(m)
        System.out.println("最大海拔高度: "+new_rcord.maxAltitude()); // 最大海拔高度(m)
        System.out.println("最低海拔: "+new_rcord.minAltitude()); // 最低海拔
//        System.out.println(new_rcord.m6026s());
//        System.out.println(new_rcord.m6025t());
//        System.out.println(new_rcord.m6024u());
//        System.out.println(new_rcord.m6023v());
        System.out.println("************************************************************");
    }

    private void get_lap_data(BicyclingRecord new_rcord) {
        for (int i = 0; true; i++) {
            try {
                BicyclingRecordLap new_lap = new_rcord.bicyclingRecordLap(i);
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

    private void get_sec_data(BicyclingRecord new_rcord) {
        for (int i = 0; true; i++) {
            try {
                BicyclingRecordLap new_lap = new_rcord.bicyclingRecordLap(i);
                new_lap.m6020a();
                for (int n = 0; true; n++) {
                    try {
                        BicyclingRecordSecData new_sec = new_lap.bicyclingRecordSecData(n);

                        System.out.println("时间戳: "+new_sec.currentTime());    // 时间戳(s)
                        System.out.println("类型: "+new_sec.dataType());
                        System.out.println("累计耗时: "+new_sec.cumulativeTotalSecond());    // 累计耗时
                        System.out.println("累计里程: "+new_sec.cumulativeTotalDistance());    // 累计里程(m)
                        System.out.println("累计卡路里: "+new_sec.cumulativeTotalCalories());    // 累计卡路里
                        System.out.println("速度: "+new_sec.speed());    // 速度(km*10/h)
                        System.out.println("踏频: "+new_sec.cadence());    // 踏频
                        System.out.println("心率: "+new_sec.hr());    // 心率
                        System.out.println("功率: "+new_sec.power());    // 功率
                        System.out.println("海拔: "+new_sec.altitude());    // 海拔
                        System.out.println("纬度: "+new_sec.latitude());    // 纬度
                        System.out.println("经度: "+new_sec.longitude());    // 经度
                        System.out.println("水平精度: "+new_sec.horizontalAccuracy());    // 水平精度
                        System.out.println("垂直精度: "+new_sec.verticalAccuracy());    // 垂直精度
                        System.out.println("#######################################");
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
    }


    public static void main( String[] args ) {
        new App().process();
        InetSocketAddress addr = new InetSocketAddress(9000);
        String absolutePath1 = new File("./").getAbsolutePath();
        Path path = Path.of(absolutePath1+"/src/main/resources/web");
        Path absolutePath = path.toAbsolutePath();
        HttpServer fileServer = SimpleFileServer.createFileServer(addr, absolutePath, SimpleFileServer.OutputLevel.VERBOSE);
        fileServer.start();
    }
}
