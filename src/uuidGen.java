import java.util.UUID;
public class uuidGen {


        public static void main(String[] args) {

            // Generating 9 UUIDs using a for loop
            for (int i = 0; i < 9; i++) {
                UUID uuid = UUID.randomUUID();
                String uuidString = uuid.toString();
                System.out.println("\"userid\" " + ": " + "\""+ uuidString +"\"");
//                System.out.println(uuidString);
            }
        }


}