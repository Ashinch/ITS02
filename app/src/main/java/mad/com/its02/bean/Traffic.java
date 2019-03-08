package mad.com.its02.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 * 红绿灯实体类
 */

@DatabaseTable(tableName = "tb_traffic")
public class Traffic {
    @DatabaseField(generatedId = true, useGetSet = true, columnName = "id")
    private int id;

    @DatabaseField(useGetSet = true, columnName = "red_time")
    private int redTime;

    @DatabaseField(useGetSet = true, columnName = "yellow_time")
    private int yellowTime;

    @DatabaseField(useGetSet = true, columnName = "green_time")
    private int greenTime;



    @Override
    public String toString() {
        return "tb_traffic{" +
                "id=" + getId() +
                ", red_time=" + getRedTime() +
                ", yellow_time=" + getYellowTime() +
                ", green_time=" + getGreenTime() +
                "}";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRedTime() {
        return redTime;
    }

    public void setRedTime(int redTime) {
        this.redTime = redTime;
    }

    public int getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(int yellowTime) {
        this.yellowTime = yellowTime;
    }

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }
}
