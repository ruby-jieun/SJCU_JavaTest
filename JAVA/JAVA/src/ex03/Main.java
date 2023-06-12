package ex03;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("고객 관리 프로그램\n");
            System.out.println("1: 고객 정보 추가");
            System.out.println("2: 고객 정보 삭제");
            System.out.println("3: 고객 정보 출력");
            System.out.println("4: 고객 정보 수정");
            System.out.println("5: 프로그램 종료\n");
            System.out.print("원하시는 기능의 숫자를 입력하세요: ");
            int number = sc.nextInt();
            System.out.println("\n");
            switch (number) {
                case 1:
                    System.out.println("고객 정보 추가\n\n");
                    addCustomer();
                    break;
                case 2:
                    System.out.println("고객 정보 삭제\n\n");
                    removeCustomer();
                    break;
                case 3:
                    System.out.println("고객 정보 출력\n\n");
                    getCustomer();
                    break;
                case 4:
                    System.out.println("고객 정보 수정\n\n");
                    setCustomer();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못 입력하셨습니다.\n");
                    break;
            }
            sc.nextLine();
        }
    }

    public static void addCustomer() {
        Scanner sc = new Scanner(System.in);

        System.out.print("고객의 번호를 입력하세요: ");
        int no = sc.nextInt();
        System.out.print("고객의 이름을 입력하세요: ");
        String name = sc.next();
        System.out.print("고객의 주소를 입력하세요: ex)서울시 중구");
        sc.nextLine();
        String address = sc.nextLine();
        System.out.print("고객의 연락처를 입력하세요 ex)010-1111-2222: ");
        String phoneNumber = sc.next();

        Customer customer = new Customer(no, name, address, phoneNumber);

        customers.add(customer);

        System.out.println(name + "고객의 정보가 입력되었습니다.\n");
    }

    public static void removeCustomer() {
        Scanner sc = new Scanner(System.in);
        getCustomer();

        System.out.print("삭제 할 고객의 번호를 입력해주세요: ");
        int n = sc.nextInt() - 1;

        if (n < 0 || n >= customers.size()) {
            System.out.println("해당 번호의 고객은 존재하지 않습니다.");
        }
        else {
            customers.remove(n);
            System.out.println((n + 1) + "번 고객의 정보를 삭제했습니다.");
        }
    }

    public static void getCustomer() {
        for (int i=0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println((i + 1) + "번 고객");
            System.out.println("=========================================");
            System.out.println("고객번호: " + customer.getNo());
            System.out.println("이름: " + customer.getName());
            System.out.println("주소: " + customer.getAddress());
            System.out.println("연락처: " + customer.getPhoneNumber());
            System.out.println();
        }
    }
    public static void setCustomer() {
        Scanner sc = new Scanner(System.in);
        getCustomer();

        System.out.print("수정 할 고객의 번호를 입력해주세요: ");
        int n = sc.nextInt() - 1;

        if (n < 0 || n >= customers.size()) {
            System.out.println("해당 번호의 고객은 존재하지 않습니다.");
        }
        else {
            Customer customer = customers.get(n);

            System.out.print("고객의 번호를 입력하세요: ");
            customer.setNo(sc.nextInt());
            System.out.print("고객의 이름을 입력하세요: ");
            customer.setName(sc.next());
            System.out.print("고객의 주소를 입력하세요: ");
            sc.nextLine();
            customer.setAddress(sc.nextLine());
            System.out.print("고객의 연락처를 입력하세요: ");
            customer.setPhoneNumber(sc.next());

            System.out.println((n + 1) + "번 고객의 정보를 수정했습니다.");
        }
    }

}

class Customer {
    private int no;
    private String name;
    private String address;
    private String phoneNumber;

    public Customer(int no, String name, String address, String phoneNumber) {
        this.no = no;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
