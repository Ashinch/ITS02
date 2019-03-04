package mad.com.its02.bean;

//  环境信息 bean 类
public class Envir {
    /**
     * pm pm2.5
     * co2 co2
     * temp 温度
     * humi 湿度
     * light 光照强度
     * warningMsg 警告信息
     */
    private  int pm;
    private   int co2;
    private   int temp;
    private   int humi;
    private   int light;
    private String warningMsg="";

    public Envir() {
        super();
    }

    public Envir(int pm, int co2, int temp, int humi, int light) {
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

    public String getWarningMsg() {
        return warningMsg;
    }

    public void setWarningMsg(String warningMsg) {
        this.warningMsg = warningMsg;
    }
}
