public class DependencyInjectionTest {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        System.out.println(service.getCustomerName("C001"));
        System.out.println(service.getCustomerName("C002"));
        System.out.println(service.getCustomerName("C999"));
    }
}
