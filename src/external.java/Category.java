public enum Category {

    PocketBook("/url1", 45),
    AirBook("/url2", 45),
    Phone("/url3", 45),
    Laptop("/url4", 45),
    NoteBook("/url5", 45);

    private String url;
    private int count;

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }

    Category(String url, int count) {
        this.url = url;
        this.count = count;
    }
}
