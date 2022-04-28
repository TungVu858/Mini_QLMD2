import java.util.ArrayList;
import java.util.List;

public class StaffManage {
    private List<Staff> staffList = new ArrayList<>();

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public void add(Staff staff) {
        staffList.add(staff);
    }

    public void displayAll() {
        boolean check = false;
        for (Staff staff : staffList) {
            System.out.println(staff);
            check = true;
        }
        if (!check) System.out.println("Chưa có nhân viên nào !!!");
    }

    public void findName(String name) {
        boolean check = false;
        for (Staff staff : staffList) {
            if (staff.getName().contains(name)) {
                System.out.println(staff);
                check = true;
            }
        }
        if (!check) System.out.println("Không tìm thấy nhân viên nào !!!!");
    }

    public int findByFullName(String name) {
        for (int i = 0; i < staffList.size(); i++) {
            if (name.equals(staffList.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }


    public void editByStatus(String name) {
        int salary = 0;
        String type = "Ở nhà ";
        String status = "off";
        for (Staff staff : staffList) {
            if (name.equals(staff.getName())) {
                staff.setType(type);
                staff.setSalary(salary);
                staff.setStatus(status);
            }
        }
    }

    public void deleteByName(String name) {
        staffList.remove(findByFullName(name));
    }

    public void edit(String name, Staff staff) {
        staffList.set(findByFullName(name), staff);
    }

    public void displayFullTime() {
        boolean check = false;
        for (Staff staff : staffList) {
            if (staff.getType().equals(Main.FULL)) {
                System.out.println(staff);
                check = true;
            }
        }
        if (!check) System.out.println("Không tìm thấy trạng thái nhân viên nào !!!");
    }

    public void displayPartTime() {
        boolean check = false;
        for (Staff staff : staffList) {
            if (staff.getType().equals(Main.PART)) {
                System.out.println(staff);
                check = true;
            }
        }
        if (!check) System.out.println("Không tìm thấy trạng thái nhân viên nào !!!");
    }

    public void displayResultAll() {
        int sum = 0;
        for (Staff staff : staffList) {
            sum += staff.getSalary();
        }
        System.out.println("Tổng tiền lương là : " + sum);
    }

    public void displayResultFullTime() {
        int sum = 0;
        for (Staff staff : staffList) {
            if (Main.FULL.equals(staff.getType())) {
                sum += staff.getSalary();
            }
        }
        System.out.println("Tổng tiền lương là : " + sum);
    }

    public void displayResultPartTime() {
        int sum = 0;
        for (Staff staff : staffList) {
            if (Main.PART.equals(staff.getType())) {
                sum += staff.getSalary();
            }
        }
        System.out.println("Tổng tiền lương là : " + sum);
    }
}
