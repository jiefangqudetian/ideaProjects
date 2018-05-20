import com.kaishengit.entity.Customer;
import com.kaishengit.util.CustomerUtil;

public class Test {

    public static void main(String[] args) {
        CustomerUtil customerUtil = new CustomerUtil();

        Customer customer = new Customer(1,"tom","shanghai");
        customerUtil.refIncrement(customer);
        System.out.println(customer);
        customer = new Customer(5,"jack","beijing");
        System.out.println(customer);

        int id = 1;
        customerUtil.simIncrement(id);
        System.out.println(id);

        String string = "tom";
        customerUtil.strIncrement(string);
        System.out.println(string);

        StringBuilder stringBuilder = new StringBuilder("tom");
        customerUtil.strbulIncrement(stringBuilder);
        System.out.println(stringBuilder);

        Integer integer = 100;
        customerUtil.simmIncrement(integer);
        System.out.println(integer);
        //increment(customer);
    }



}
