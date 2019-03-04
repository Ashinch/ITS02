package mad.com.its02.config;

//  请求地址端口配置信息类
public class AppConfig {
    //  请求端口
    public static final int PORT = 8080;

    //  请求获取 小车速度 地址
    public static final String GET_CAR_SPEED = "GetCarSpeed.do";
    //  请求获取 小车账户余额 地址
    public static final String GET_CAR_BALANCE = "GetCarAccountBalance.do";
    //  请求获取 停车场费率 地址
    public static final String GET_PARK_RATE = "GetParkRate.do";
    //  请求获取 红绿灯信息 地址
    public static final String GET_LIGHT_MSG = "GetTrafficLightConfigAction.do";
    //  请求获取 停车场空闲车位信息 地址
    public static final String GET_FREE_LOCATION = "GetParkFree.do";
    //  请求获取 所有传感器信息 地址
    public static final String GET_ALL_SENSOR = "GetAllSense.do";
    //  请求获取 光照传感器阀值信息 地址
    public static final String GET_SENSOR_LIGHT = "GetLightSenseValue.do";
    //  请求获取 公交站台信息 地址
    public static final String GET_BUS_STATION_INFO = "GetBusStationInfo.do";
    //  请求获取 道路拥挤状态 地址
    public static final String GET_ROAD_STATION_INFO = "GetRoadStatus.do";

    //  请求设置 小车动作 地址
    public static final String SET_CAR_ACTION = "SetCarMove.do";
    //  请求设置 小车账户余额 地址
    public static final String SET_CAR_BALANCE = "SetCarAccountRecharge.do";
    //  请求设置 小车动作 地址
    public static final String SET_PARK_RATE = "SetParkRate.do";

    //  json 键 小车 id
    public static final String KEY_CAR_ID = "CarId";
    //  json 键 小车速度
    public static final String KEY_CAR_SPEED = "CarSpeed";
    //  json 键 小车动作
    public static final String KEY_CAR_ACTION = "CarAction";
    //  json 键 小车账户余额
    public static final String KEY_CAR_BALANCE = "Balance";

    //  json 键 小车充值金额、停车场费率金额
    public static final String KEY_MONEY = "Money";
    //  json 键 停车场费率类型
    public static final String KEY_RATE_TYPE = "RateType";
    //  json 键 停车场空闲车位 id
    public static final String KEY_PARK_FREE_ID = "ParkFreeId";

    //  json 键 红绿灯 id
    public static final String KEY_TRAFFIC_LIGHT_ID = "TrafficLightId";
    //  json 键 黄灯时间
    public static final String KEY_LIGHT_YELLOW = "YellowTime";
    //  json 键 绿灯时间
    public static final String KEY_LIGHT_GREEN = "GreenTime";
    //  json 键 红灯时间
    public static final String KEY_LIGHT_RED = "RedTime";

    //  json 键 光照传感器阀值 down
    public static final String KEY_LIGHT_DOWN = "Down";
    //  json 键 光照传感器阀值 up
    public static final String KEY_LIGHT_UP = "Up";

    //  json 键 传感器信息 pm2.5
    public static final String KEY_SENSOR_PM = "pm2.5";
    //  json 键 传感器信息 co2
    public static final String KEY_SENSOR_CO2 = "co2";
    //  json 键 传感器信息 光照强度
    public static final String KEY_SENSOR_LIGHT = "LightIntensity";
    //  json 键 传感器信息 温度
    public static final String KEY_SENSOR_TEMP = "temperature";
    //  json 键 传感器信息 湿度
    public static final String KEY_SENSOR_HUMI = "humidity";

    //  json 键 公交车 id
    public static final String KEY_BUS_ID = "BusId";
    //  json 键 公交站台 id
    public static final String KEY_BUS_STATION_ID = "BusStationId";
    //  json 键 公交车与公交站台距离
    public static final String KEY_DISTANCE = "Distance";

    //  json 键 道路 id
    public static final String KEY_ROAD_ID = "RoadId";
    //  json 键 道路拥挤状态
    public static final String KEY_STATUS = "Status";
}
