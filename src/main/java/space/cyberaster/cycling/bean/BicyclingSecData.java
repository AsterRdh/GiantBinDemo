package space.cyberaster.cycling.bean;

import space.cyberaster.cycling.ginat.BicyclingRecordSecData;

public class BicyclingSecData {

    public long currentTime;// 时间戳
    public int cumulativeTotalSecond; // 累计耗时
    public float cumulativeTotalDistance;  // 累计里程(m)
    public float cumulativeTotalCalories;    // 累计卡路里
    public int speed;// 速度(km*10/h)
    public int cadence;// 踏频
    public int hr;// 心率
    public float power;// 功率
    public double altitude;// 海拔
    public double latitude;// 纬度
    public double longitude;// 经度
    public int horizontalAccuracy;// 水平精度
    public int verticalAccuracy;// 垂直精度



    public BicyclingSecData(BicyclingRecordSecData newSec) {
        currentTime = newSec.currentTime();// 时间戳
        cumulativeTotalSecond = newSec.cumulativeTotalSecond(); // 累计耗时
        cumulativeTotalDistance = newSec.cumulativeTotalDistance();  // 累计里程(m)
        cumulativeTotalCalories = newSec.cumulativeTotalCalories();    // 累计卡路里
        speed = newSec.speed();// 速度(km*10/h)
        cadence = newSec.cadence();// 踏频
        hr = newSec.hr();// 心率
        power = newSec.power();// 功率
        altitude = newSec.altitude();// 海拔
        latitude = newSec.latitude();// 纬度
        longitude = newSec.longitude();// 经度
        horizontalAccuracy = newSec.horizontalAccuracy();// 水平精度
        verticalAccuracy = newSec.verticalAccuracy();// 垂直精度
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public int getCumulativeTotalSecond() {
        return cumulativeTotalSecond;
    }

    public void setCumulativeTotalSecond(int cumulativeTotalSecond) {
        this.cumulativeTotalSecond = cumulativeTotalSecond;
    }

    public float getCumulativeTotalDistance() {
        return cumulativeTotalDistance;
    }

    public void setCumulativeTotalDistance(float cumulativeTotalDistance) {
        this.cumulativeTotalDistance = cumulativeTotalDistance;
    }

    public float getCumulativeTotalCalories() {
        return cumulativeTotalCalories;
    }

    public void setCumulativeTotalCalories(float cumulativeTotalCalories) {
        this.cumulativeTotalCalories = cumulativeTotalCalories;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    public void setHorizontalAccuracy(int horizontalAccuracy) {
        this.horizontalAccuracy = horizontalAccuracy;
    }

    public int getVerticalAccuracy() {
        return verticalAccuracy;
    }

    public void setVerticalAccuracy(int verticalAccuracy) {
        this.verticalAccuracy = verticalAccuracy;
    }
}
