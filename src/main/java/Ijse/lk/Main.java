package Ijse.lk;

import Ijse.lk.Entities.Autor;
import Ijse.lk.Util.FactoryConfiguration;
import Ijse.lk.Entities.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        Autor autor = new Autor();
        autor.setId("A0099");
        autor.setName("Greg");

        session.save(autor);


        transaction.commit();
        session.close();
    }
}
