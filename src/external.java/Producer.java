public enum Producer {

    Amoi(5, "media/iphone1.png"),
    BYD(9, "media/iphone2.png"),
    Electronic(25, "media/iphone3.png"),
    Changhong(33, "media/iphone4.png"),
    Gionee(23, "media/iphone5.png"),
    Haier(98, "media/iphone6.png"),
    Konka(43, "media/iphone0.png"),
    Group(98, "media/iphone11.png"),
    Lenovo(23, "media/iphone12.png"),
    Meizu(4,"media/iphone13.png");

    public int count;
    private String url;

    Producer(int count, String url) {
        this.count = count;
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }
}
