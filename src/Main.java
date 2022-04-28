import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        StaffManage staffManage = new StaffManage();
        Scanner scc = new Scanner(System.in);
        Scanner scs = new Scanner(System.in);
        String name;
        String type;
        String status;
        int salary;
        int choice = -1;
        while (choice != 7) {
            staffManage.setStaffList(StaffFile.readFromFile(PATH));
            System.out.println("-----Quản lý nhân viên-----");
            System.out.println("Chọn chức năng theo số (để tiếp tục) : ");
            System.out.println("0. Hiển thị tất cả nhân viên ");
            System.out.println("1. Thêm ");
            System.out.println("2. Tìm kiếm(theo tên) ");
            System.out.println("3. In ra danh sách fulltime ");
            System.out.println("4. Sửa ");
            System.out.println("5. Đổi trạng thái (đang làm việc sang nghỉ hoặc đi làm lại) ");
            System.out.println("6. Tính tổng lương ");
            System.out.println("7. Thoát ");
            System.out.print("Chọn chức năng : ");
            choice = getChoice(scs, choice);
            switch (choice) {
                case 0:
                    System.out.println("Danh sách các nhân viên ");
                    staffManage.displayAll();
                    break;
                case 1:
                    System.out.println("Nhập tên nhân viên : ");
                    name = scc.nextLine().trim();
                    System.out.println("Nhập loại công việc nhân viên full/part: ");
                    type = scc.nextLine();
                    System.out.println("Nhập trạng thái nhân viên on/off: ");
                    status = scc.nextLine();
                    salary = checkExceptionNumber("Nhập tiền lương của nhân viên : ");
                    staffManage.add(new Staff(name, type, status, salary));
                    StaffFile.writeToFile(PATH, staffManage.getStaffList());
                    System.out.println("Thêm thành công " + name);
                    break;
                case 2:
                    System.out.println("Nhập tên nhân viên : ");
                    name = scc.nextLine();
                    System.out.println("Nhân viên tìm được ");
                    staffManage.findName(name);
                    break;
                case 3:
                    staffManage.displayFullTime();
                    staffManage.displayResultFullTime();
                    break;
                case 4:
                    System.out.println("Nhập tên nhân viên cần sửa : ");
                    name = scc.nextLine();
                    if (staffManage.findByFullName(name) != -1) {
                        System.out.println("Nhập loại công việc mới full/part: ");
                        type = scc.nextLine();
                        System.out.println("Nhập trạng thái mới on/off: ");
                        status = scc.nextLine();
                        salary = checkExceptionNumber("Nhập lương mới : ");
                        staffManage.edit(name, new Staff(name, type, status, salary));
                        System.out.println("Bạn đã sửa thành công " + name);
                        StaffFile.writeToFile(PATH, staffManage.getStaffList());
                    } else {
                        System.out.println("Không tìm thấy nhân viên này !!!!");
                    }
                    break;
                case 5:
                    System.out.println("Nhập tên nhân viên cần cập nhật trạng thái : ");
                    name = scc.nextLine();
                    if (staffManage.findByFullName(name) != -1) {
                        System.out.println("Hiển thị trạng thái nhiên viên : ");
                        staffManage.findName(name);
                        System.out.println("Cập nhật trạng thái off/on");
                        status = scc.nextLine();
                        if (status.equals("off")) {
                            staffManage.editByStatus(name);
                            StaffFile.writeToFile(PATH, staffManage.getStaffList());
                            System.out.println(name + " đã được cho nghỉ !!!");
                        } else if (status.equals("on")) {
                            System.out.println("Nhập loại công việc nhân viên full/part: ");
                            type = scc.nextLine();
                            salary = checkExceptionNumber("Nhập lương nhân viên : ");
                            staffManage.edit(name, new Staff(name, type, status, salary));
                            StaffFile.writeToFile(PATH, staffManage.getStaffList());
                            System.out.println("Chào mừng " + name + " quay lại làm việc !!!");
                        } else System.out.println("Bạn chưa thay đổi trạng thái cho nhân viên !!!");

                    } else System.out.println("Không tìm thấy nhân viên này !!!");
                    break;
                case 6:
                    System.out.println("Toàn bộ nhân viên : ");
                    staffManage.displayAll();
                    staffManage.displayResultAll();
                    System.out.println("Nhân viên fulltime : ");
                    staffManage.displayFullTime();
                    staffManage.displayResultFullTime();
                    System.out.println("Nhân viên parttime : ");
                    staffManage.displayPartTime();
                    staffManage.displayResultPartTime();
                    break;
            }
        }
    }

    public static final String FULL = "full";
    public static final String PART = "part";
    public static final String PATH = "src/staff.csv";

    public static int checkExceptionNumber(String message) {
        int choice = -1;
        Scanner scs = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            try {
                System.out.println(message);
                choice = scs.nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Chỉ được nhập số !!!!!" + ANSI_RESET);
                scs.nextLine();
            }
        }
        return choice;
    }

    public static int getChoice(Scanner scs, int choice) {
        try {
            choice = scs.nextInt();
            if (choice > 7 || choice < 0) throw new Exception();
        } catch (InputMismatchException e) {
            System.out.println(ANSI_RED + "Không được nhập chữ !!!" + ANSI_RESET);
            scs.nextLine();
            choice = -1;
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Chỉ được nhập số từ 0 --> 7" + ANSI_RESET);
        }
        return choice;
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
}
