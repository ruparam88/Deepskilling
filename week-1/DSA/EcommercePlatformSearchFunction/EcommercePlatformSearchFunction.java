import java.util.Arrays;
import java.util.Comparator;

class Product {
    String productId;
    String productName;
    String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}

public class EcommercePlatformSearchFunction {
    public static Product linearSearch(Product[] products, String productId) {
        for (Product p : products) {
            if (p.productId.equals(productId)) return p;
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String productId) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = products[mid].productId.compareTo(productId);
            if (cmp == 0) return products[mid];
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
    }
}