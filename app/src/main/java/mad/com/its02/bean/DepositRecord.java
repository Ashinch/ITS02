package mad.com.its02.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 * 充值记录实体类
 */

@DatabaseTable(tableName = "tb_deposit_record")
public class DepositRecord {
    @DatabaseField(generatedId = true, useGetSet = true, columnName = "id")
    private int id;

    @DatabaseField(useGetSet = true, columnName = "user_id")
    private int userId;

    @DatabaseField(useGetSet = true, columnName = "user_name")
    private String userName;

    @DatabaseField(useGetSet = true, columnName = "car_id")
    private int carId;

    @DatabaseField(useGetSet = true, columnName = "money")
    private int money;

    @DatabaseField(useGetSet = true, columnName = "deposit_date")
    private String depositDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    @Override
    public String toString() {
        return "tb_deposit_record{" +
                "id=" + id +
                ", user_id=" + userId +
                ", user_name='" + userName + "\'" +
                ", car_id=" + carId +
                ", money=" + money +
                ", deposit_date='" + depositDate + "\'" +
                "}";
    }


}
