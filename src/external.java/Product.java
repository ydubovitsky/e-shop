import java.util.Random;

public enum  Product {

    Nokia(generateName(), descriptionGenerator(), linkGenerator(), priceGenerator(), idCategoryGen(), idProducerGen()),
    Motorola(generateName(), descriptionGenerator(), linkGenerator(), priceGenerator(), idCategoryGen(), idProducerGen()),
    Iphone(generateName(), descriptionGenerator(), linkGenerator(), priceGenerator(), idCategoryGen(), idProducerGen()),
    Siemens(generateName(), descriptionGenerator(), linkGenerator(), priceGenerator(), idCategoryGen(), idProducerGen()),
    Samsung(generateName(), descriptionGenerator(), linkGenerator(), priceGenerator(), idCategoryGen(), idProducerGen()),
    LG(generateName(), descriptionGenerator(), linkGenerator(), priceGenerator(), idCategoryGen(), idProducerGen()),
    Sony(generateName(), descriptionGenerator(), linkGenerator(), priceGenerator(), idCategoryGen(), idProducerGen());

    private String name;
    private String description;
    private String imageLink;
    private double price;
    private int idCategory;
    private int idProducer;

    Product(String name, String description, String link, double price, int idCategory, int idProducer) {
        this.name = name;
        this.description = description;
        this.imageLink = link;
        this.price = price;
        this.idCategory = idCategory;
        this.idProducer = idProducer;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public double getPrice() {
        return price;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public int getIdProducer() {
        return idProducer;
    }

    private static String generateName() {
        StringBuilder productCode = new StringBuilder();
        Random random = new Random();
        for (int i = random.nextInt(2) + 1; i >= 0; i--) {
            productCode.append((char) ('A' + random.nextInt(22)));
        }
        for (int i = random.nextInt(5) + 3; i >= 0; i--) {
            productCode.append(String.valueOf(random.nextInt(10)));
        }
        return productCode.toString();
    }

    private static String descriptionGenerator() {

        String[] E_BOOK_DESC = {"Monitor diagonal {6,12,1}\"", "[Matrix type: E lnk;Matrix type: E lnk Pearl;Matrix type: E lnk Carta]", "[Resolution: 800x600;Resolution: 1600x1200;Resolution: 1024x758;Resolution: 1200x800;Resolution: 800x600]", "Memory {4,12,1} GB", "Weight {155,200,5} g", "Memory {4,20,4} Gb", "Weight {20,100,3} g", "[Silver;Blue;Orange;White;Black]", "MP3", "Monitor diagonal {1.1,1.8,0.1}\"", "[RAM: 512 Mb;RAM: 256 Mb;RAM: 512 Mb;RAM: 1 Gb]", "[Silver;Blue;Orange;White;Black]", "{1000,2000,100} mA/h", "Weight {60,300,40} g"};
        return E_BOOK_DESC[new Random().nextInt(E_BOOK_DESC.length)];
    }

    private static String linkGenerator() {
        return "/url/" + new Random().nextInt(500);
    }

    private static double priceGenerator() {
        return new Random().nextDouble();
    }

    private static int idCategoryGen() {
        return randomInt(Category.values().length);
    }

    private static int idProducerGen() {
        return randomInt(Producer.values().length );
    }

    /**
     * Генерация случайного числа в диапазоне от 1 до max
     * @param max - максимальное случайное число
     * @return
     */
    public static int randomInt(int max){
        int a = 1; // Начальное значение диапазона - "от"
        int randomNumber = a + (int) (Math.random() * max); // Генерация 1-го числа
        return randomNumber;
    }
}
