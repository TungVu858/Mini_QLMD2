import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffFile {
    public static void writeToFile(String path, List<Staff> staff) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        StringBuilder str = new StringBuilder("name,loại,trạng thái,lương \n");
        for (Staff value : staff) {
            str.append(value.getName()).append(",").append(value.getType()).append(",").append(value.getStatus()).append(",").append(value.getSalary()).append("\n");
        }
        bufferedWriter.write(str.toString());
        bufferedWriter.close();
    }

    public static List<Staff> readFromFile(String path) throws IOException {
        List<Staff> staff = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str = bufferedReader.readLine();
        while ((str = bufferedReader.readLine()) != null) {
            String[] value = str.split(",");
            staff.add(new Staff(value[0], value[1], value[2], Integer.parseInt(value[3])));
        }
        return staff;
    }
}
