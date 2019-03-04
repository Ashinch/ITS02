package mad.com.its02.bean;

//  菜单 bean 类
public class MenuModel {
    /**
     * icon 图标
     * content 内容
     */
    private int icon;
    private String content;

    public MenuModel() {
        super();
    }

    public MenuModel(int icon, String content) {
        this.icon = icon;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
