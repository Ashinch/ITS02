package mad.com.its02.bean;

import android.content.Context;

import mad.com.its02.R;

import java.util.ArrayList;
import java.util.List;
//  传感器 bean 类
public class SensorBean {
    public static final int PM_HIGHT = 0x10;
    public static final int LIGHT_HIGHT = 0x11;
    public static final int LIGHT_LOW = 0x12;
    public static final int CO2_HIGHT = 0x13;
    public static final int TEMP_HIGHT = 0x14;
    public static final int TEMP_LOW = 0x15;
    public static final int HUMI_HIGHT = 0x16;
    public static final int HUMI_LOW = 0x17;
    private int pm = 0;
    private int pmMax = 200;
    // private int pmMin = 0;
    private int co2 = 0;
    private int co2Max = 5000;
    // private int co2Min = 0;
    private int temp = 0;
    private int tempMax = 40;
    private int tempMin = 10;
    private int humi = 0;
    private int humiMax = 50;
    private int humiMin = 0;
    private int light = 0;
    private int lightMax = 2500;
    private int lightMin = 1000;

    public SensorBean() {
        super();
    }

    public SensorBean(int pm, int co2, int temp, int humi, int light) {
        super();
        this.pm = pm;
        this.co2 = co2;
        this.temp = temp;
        this.humi = humi;
        this.light = light;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumi() {
        return humi;
    }

    public void setHumi(int humi) {
        this.humi = humi;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public List<Integer> warning() {
        List<Integer> lists = new ArrayList<Integer>();
        if (pm > pmMax) {
            lists.add(PM_HIGHT);
        }
        if (co2 > co2Max) {
            lists.add(CO2_HIGHT);
        }
        if (temp > tempMax) {
            lists.add(TEMP_HIGHT);
        }
        if (temp < tempMin) {
            lists.add(TEMP_LOW);
        }
        if (humi > humiMax) {
            lists.add(HUMI_HIGHT);
        }
        if (humi < humiMin) {
            lists.add(HUMI_LOW);
        }
        if (light > lightMax) {
            lists.add(LIGHT_HIGHT);
        }
        if (light < lightMin) {
            lists.add(LIGHT_LOW);
        }
        return lists;
    }

    public int lightWarning() {
        int warning = 0;
        if (light > lightMax) {
            warning = LIGHT_HIGHT;
        }
        if (light < lightMin) {
            warning = LIGHT_LOW;
        }
        return warning;
    }

    public int pmWarning() {
        int warning = 0;
        if (pm > pmMax) {
            warning = PM_HIGHT;
        }
        return warning;
    }

//    public String warningEnvirMsg(Context context) {
//        String envirMsg = "";
//        for (int i : warning()) {
//            switch (i) {
//                case PM_HIGHT:
//                    envirMsg += context.getString(R.string.msg_pm_pollute);
//                    break;
//                case CO2_HIGHT:
//                    envirMsg += context.getString(R.string.msg_co2_high);
//                    break;
//                case TEMP_HIGHT:
//                    envirMsg += context.getString(R.string.msg_temp_high);
//                    break;
//                case TEMP_LOW:
//                    envirMsg += context.getString(R.string.msg_temp_low);
//                    break;
//                case HUMI_HIGHT:
//                    envirMsg += context.getString(R.string.msg_humi_high);
//                    break;
//                case HUMI_LOW:
//                    envirMsg += context.getString(R.string.msg_humi_low);
//                    break;
//            }
//        }
//        return envirMsg;
//    }
//
//    public String warningLightMsg(Context context) {
//        String lightMsg = "";
//        for (int i : warning()) {
//            switch (i) {
//                case LIGHT_HIGHT:
//                    lightMsg = context.getString(R.string.msg_light_high);
//                    break;
//                case LIGHT_LOW:
//                    lightMsg = context.getString(R.string.msg_light_low);
//                    break;
//
//            }
//        }
//        return lightMsg;
//    }
}
