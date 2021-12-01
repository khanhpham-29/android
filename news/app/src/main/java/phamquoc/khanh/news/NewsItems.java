package phamquoc.khanh.news;

public class NewsItems {
    private String Name;
    private int Logo;
    private String[][] UrlCaptionMenu;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLogo() {
        return Logo;
    }

    public void setLogo(int logo) {
        Logo = logo;
    }

    public String[][] getUrlCaptionMenu() {
        return UrlCaptionMenu;
    }

    public void setUrlCaptionMenu(String[][] urlCaptionMenu) {
        UrlCaptionMenu = urlCaptionMenu;
    }



    public NewsItems(String name, int Logo, String[][] UrlCaptionMenu) {
        this.Name = name;
        this.Logo = Logo;
        this.UrlCaptionMenu = UrlCaptionMenu;
    }


}
