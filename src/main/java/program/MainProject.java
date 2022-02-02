package program;

import org.hibernate.Session;
import utils.DbContext;

public class MainProject {
    public static Session session = DbContext.getSessionFactory().openSession();
    public static void main(String[] args) {
    System.out.println("Hello");
    }
}
