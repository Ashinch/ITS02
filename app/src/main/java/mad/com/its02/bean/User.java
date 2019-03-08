package mad.com.its02.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ash on 2019/3/4.
 */


/*
 * 用户实体类
 */

@DatabaseTable(tableName = "tb_user")
public class User {

    //generatedId定义主键自增长，columnName定义该字段在数据库中的列名

    // id
    @DatabaseField(useGetSet = true, generatedId = true, columnName = "id")
    private int id;

    // 用户名
    @DatabaseField(useGetSet = true, columnName = "username", unique = true)
    private String username;

    // 密码
    @DatabaseField(useGetSet = true, columnName = "password")
    private String password;

    // 权限 0普通 1管理员
    @DatabaseField(useGetSet = true, columnName = "permission", defaultValue = "0")
    private int permission;

    // 性别 0男 1女
    @DatabaseField(useGetSet = true, columnName = "sex", defaultValue = "0")
    private int sex;

    // 手机号
    @DatabaseField(useGetSet = true, columnName = "phone_num")
    private String phoneNum;

    // 注册时间
    @DatabaseField(useGetSet = true, columnName = "join_date")
    private String joinDate;

    // 身份证号
    @DatabaseField(useGetSet = true, columnName = "id_num")
    private String idNum;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                ", sex=" + sex +
                ", phoneNum=" + phoneNum +
                ", joinDate='" + joinDate + '\'' +
                ", idNum=" + idNum +
                '}';
    }
}
