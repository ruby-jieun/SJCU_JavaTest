package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Emp> emps;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        emps = loadEmpFile();

        while(true) {
            System.out.println("사원 관리 프로그램\n");
            System.out.println("1: 사원 정보 추가");
            System.out.println("2: 사원 정보 삭제");
            System.out.println("3: 사원 정보 출력");
            System.out.println("4: 사원 정보 수정");
            System.out.println("5: 사원 정보 저장");
            System.out.println("6: 프로그램 종료\n");
            System.out.print("원하시는 기능의 숫자를 입력하세요: ");
            int number = sc.nextInt();
            System.out.println("\n");
            switch (number) {
                case 1:
                    System.out.println("사원 정보 추가\n\n");
                    addEmp();
                    break;
                case 2:
                    System.out.println("사원 정보 삭제\n\n");
                    removeEmp();
                    break;
                case 3:
                    System.out.println("사원 정보 출력\n\n");
                    getEmp();
                    break;
                case 4:
                    System.out.println("사원 정보 수정\n\n");
                    setEmp();
                    break;
                case 5:
                    System.out.println("사원 정보 저장\n\n");
                    saveEmp();
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다");
                    System.out.print("수정 사항을 저장하시겠습니까?(y/n): ");
                    sc.nextLine();
                    String yn = sc.next();
                    if (yn.equals("y") || yn.equals("Y")) {
                        saveEmp();
                        System.exit(0);
                    }
                    else if(yn.equals("n") || yn.equals("N")) System.exit(0);
                    else System.out.println("잘못 입력하셨습니다.\n");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.\n");
                    break;
            }
            sc.nextLine();
        }
    }

    public static void addEmp() {
        Scanner sc = new Scanner(System.in);

        System.out.print("사원의 이름을 입력하세요: ");
        String name = sc.next();
        System.out.print("사원의 연락처를 입력하세요: ");
        String number = sc.next();
        System.out.print("사원의 부서를 입력하세요: ");
        String dept = sc.next();
        System.out.print("사원의 직급을 입력하세요: ");
        String rank = sc.next();
        System.out.print("사원의 급여를 입력하세요: ");
        String salary = sc.next();

        Emp emp = new Emp(name, number, dept, rank, salary);

        emps.add(emp);

        System.out.println(name + "사원의 정보가 입력되었습니다.\n");
    }

    public static void removeEmp() {
        Scanner sc = new Scanner(System.in);
        getEmp();

        System.out.print("삭제 할 사원의 번호를 입력해주세요: ");
        int n = sc.nextInt() - 1;

        if (n < 0 || n >= emps.size()) {
            System.out.println("해당 번호의 사원은 존재하지 않습니다.");
        }
        else {
            emps.remove(n);
            System.out.println((n + 1) + "번 사원의 정보를 삭제했습니다.");
        }
    }

    public static void getEmp() {
        for (int i=0; i < emps.size(); i++) {
            Emp emp = emps.get(i);
            System.out.println((i + 1) + "번 사원");
            System.out.println("=========================");
            System.out.println("이름: " + emp.getName());
            System.out.println("연락처: " + emp.getNumber());
            System.out.println("부서: " + emp.getDept());
            System.out.println("직급: " + emp.getRank());
            System.out.println("연봉: " + emp.getSalary());
            System.out.println();
        }
    }
    public static void setEmp() {
        Scanner sc = new Scanner(System.in);
        getEmp();

        System.out.print("수정 할 사원의 번호를 입력해주세요: ");
        int n = sc.nextInt() - 1;

        if (n < 0 || n >= emps.size()) {
            System.out.println("해당 번호의 사원은 존재하지 않습니다.");
        }
        else {
            Emp emp = emps.get(n);

            System.out.print("사원의 이름을 입력하세요: ");
            emp.setName(sc.next());
            System.out.print("사원의 연락처를 입력하세요: ");
            emp.setNumber(sc.next());
            System.out.print("사원의 부서를 입력하세요: ");
            emp.setDept(sc.next());
            System.out.print("사원의 직급을 입력하세요: ");
            emp.setRank(sc.next());
            System.out.print("사원의 급여를 입력하세요: ");
            emp.setSalary(sc.next());

            System.out.println((n + 1) + "번 사원의 정보를 수정했습니다.");
        }
    }

    public static void saveEmp() {
        try (FileWriter writer = new FileWriter("emp.txt")) {
            for (Emp emp : emps) {
                writer.write(emp.getName() + ", " + emp.getNumber() + ", " + emp.getDept() + ", " + emp.getRank() + ", " + emp.getSalary() + "\n");
            }
            getEmp();
            System.out.println("파일 저장 완료");
        }
        catch (Exception e) {
            System.out.println("파일 저장 실패: " + e);
        }
    }

    public static ArrayList<Emp> loadEmpFile() throws IOException {
        // emp.txt 파일이 존재하는지 확인합니다.
        File file = new File("emp.txt");
        if (!file.exists()) {
            // emp.txt 파일이 존재하지 않으면 새로운 파일을 생성합니다.
            file.createNewFile();
        }

        // emp.txt 파일을 읽습니다.
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<>();
        String line;

        ArrayList<Emp> ret = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(", ");

            Emp emp = new Emp(
                    temp[0],
                    temp[1],
                    temp[2],
                    temp[3],
                    temp[4]
            );

            ret.add(emp);
        }
        reader.close();

        return ret;
    }
}

class Emp {
    private String name;
    private String number;
    private String dept;
    private String rank;
    private String salary;

    public Emp(String name, String number, String dept, String rank, String salary) {
        this.name = name;
        this.number = number;
        this.dept = dept;
        this.rank = rank;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
