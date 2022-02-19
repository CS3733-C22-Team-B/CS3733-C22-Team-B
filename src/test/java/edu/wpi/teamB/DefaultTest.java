/*-------------------------*/
/* DO NOT DELETE THIS TEST */
/*-------------------------*/

package edu.wpi.teamB;

import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.Employee;
import edu.wpi.cs3733.c22.teamB.entity.PasswordHashing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefaultTest {

    @Test
    public void test() {
        String password = "Aqwe12";
        System.out.println(PasswordHashing.hashPassword(password));

        DatabaseWrapper db = new DatabaseWrapper();
        Employee employee = new Employee("ASD", "asd", "ad", "asd", 12, "asd", "asd", "Asd", "asd");
//        db.addEmployee(employee);
        assertEquals(db.getEmployee("ASD").getPassword(), PasswordHashing.hashPassword("asd"));
    }
}
