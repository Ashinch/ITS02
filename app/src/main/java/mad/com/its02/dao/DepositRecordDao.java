package mad.com.its02.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mad.com.its02.bean.DepositRecord;

/*
 *      充值记录dao
 */

public class DepositRecordDao {
    private Context context;
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<DepositRecord, Integer> dao;

    public DepositRecordDao(Context context) {
        this.context = context;
        try {
            this.dao = DatabaseHelper.getInstance(context).getDao(DepositRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 向depositRecords表中添加一条数据
    public void insert(DepositRecord data) {
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除depositRecords表中的一条数据
    public void delete(DepositRecord data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改depositRecords表中的一条数据
    public void update(DepositRecord data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询depositRecords表中的所有数据
    public List<DepositRecord> selectAll() {
        List<DepositRecord> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据ID取出用户信息
    public DepositRecord queryById(int id) {
        DepositRecord depositRecord = null;
        try {
            depositRecord = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositRecord;
    }

    // 根据useId取出信息
    public List<DepositRecord> queryByUserId(String name) {
        List<DepositRecord> depositRecords= new ArrayList<DepositRecord>();
        try {
            depositRecords = dao.queryBuilder().where().eq("user_id", name).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositRecords;
    }

}
