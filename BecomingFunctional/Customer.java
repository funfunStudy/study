package chapter3;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    // 모든 고객 정보리스트
    static public ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    public Integer id = 0;
    public String name = "";
    public String address = "";
    public String state = "";
    public String primaryContact = "";
    public String domain = "";
    public Boolean enabled = true;
    public Contract contract;

    public Customer() {
    }

    /**
     * 초기화
     */
    static {
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.name = "name : " + i;
            customer.address = "address : " + i;
            customer.state = "state : " + i;
            customer.primaryContact = "primaryContact : " + i;
            customer.domain = "domain : " + i;
            customer.enabled = i % 2 == 0;
            allCustomers.add(customer);
        }
    }

    public Customer setCustomerId(Integer customer_id) {
        this.id = customer_id;
        return this;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public Customer setState(String state) {
        this.state = state;
        return this;
    }

    public Customer setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public Customer setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Customer setContract(Contract contract) {
        this.contract = contract;
        return this;
    }

    /**
     * 고객정보를 가지고오는 정보 클래스
     */
    public static List<String> getEnabledCustomerAddresses() {
        return getEnabledCustomerField(new Function1<Customer, String>() {
            public String call(Customer customer) {
                return customer.address;
            }
        });
    }

    public static List<String> getEnabledCustomerNames() {
        return getEnabledCustomerField(new Function1<Customer, String>() {
            public String call(Customer customer) {
                return customer.name;
            }
        });
    }

    public static List<String> getEnabledCustomerStates() {
        return getEnabledCustomerField(new Function1<Customer, String>() {
            public String call(Customer customer) {
                return customer.state;
            }
        });
    }

    public static List<String> getEnabledCustomerPrimaryContacts() {
        return getEnabledCustomerField(new Function1<Customer, String>() {
            public String call(Customer customer) {
                return customer.primaryContact;
            }
        });
    }

    public static List<String> getEnabledCustomerDomains() {
        return getEnabledCustomerField(new Function1<Customer, String>() {
            public String call(Customer customer) {
                return customer.domain;
            }
        });
    }

    public static <B> List<B> getEnabledCustomerField(Function1<Customer, B> func) {
        ArrayList<B> outList = new ArrayList<B>();
        for (Customer customer : Customer.allCustomers) {
            if (customer.enabled) {
                outList.add(func.call(customer));
            }
        }
        return outList;
    }

    /**
     * id에 해당하는 고객이 존재하면 반환하고, 그렇지 않으면 null을 반환하는 간단한 메소드
     *
     * @param customer_id
     * @return
     */
    public static Customer getCustomerById(Integer customer_id) {
        for (Customer customer : Customer.allCustomers) {
            if (customer.id == customer_id) {
                return customer;
            }
        }
        // 이걸 대체 무슨 의미로 받아들여야 할까요?
        //  - 에러인가? 못찾았다는 것인가?
        return null;
    }

    /**
     * 프로그램 오류는 아닌데도 호출자에게 ‘고객을 찾지 못했으니 예외를 던지는 거 야’라고 말하는 건 좀 이상하므로 위의 getCustomerById 보완
     *
     * @param customer_id
     * @return
     */
    public static ArrayList<Customer> getCustomerListById(Integer customer_id) {
        ArrayList<Customer> outList = new ArrayList<Customer>();
        for (Customer customer : Customer.allCustomers) {
            if (customer.id == customer_id) {
                outList.add(customer);
            }
        }
        return outList;
    }

    /**
     * 일급함수이지만 순수함수는 아니다. 참조된 allCustomers값에 의해서 변경될 소지가 있기 때문이다
     *
     * @param func
     * @return
     */
    public static ArrayList<Customer> filter(Function1<Customer, Boolean> func) {
        ArrayList<Customer> outList = new ArrayList<Customer>();
        for (Customer customer : Customer.allCustomers) {
            if (func.call(customer)) {
                outList.add(customer);
            }
        }
        return outList;
    }

    /**
     * filter 메소드를 이용해서 getCustomerListById 개선한 메소드
     * 함수를 더 작은 순수 함수들로 나누어 작성하면 코드의 전반적인 기능을 잘 이해하고 파악하는 데 도움이 될 수 있음
     *
     * @param customer_id
     * @return
     */
    public static ArrayList<Customer> getCustomerListFilterById(final Integer customer_id) {
        return Customer.filter(new Function1<Customer, Boolean>() {
            public Boolean call(Customer customer) {
                return customer.id == customer_id;
            }
        });
    }

    /**
     *
     * @param function1
     * @param function2
     * @param <B>
     * @return
     */
    public static <B> List<B> getField(Function1<Customer, Boolean> function1, Function1<Customer, B> function2) {
        ArrayList<B> outList = new ArrayList<B>();
        for (Customer customer : Customer.filter(function1)) {
            outList.add(function2.call(customer));
        }
        return outList;
    }

    // 테스트
    public static void main(String[] args) {
        List<String> addresses = getEnabledCustomerAddresses();
        for (String address : addresses) {
            System.out.print(address + " ");
        }
        System.out.println();
        List<String> names = getField(
                new Function1<Customer, Boolean>() {
                    @Override
                    public Boolean call(Customer in1) {
                        return in1.enabled;
                    }
                }, new Function1<Customer, String>() {
                    @Override
                    public String call(Customer in1) {
                        return in1.name;
                    }
                });
        for (String name : names) {
            System.out.print(name + " ");
        }
    }
}
