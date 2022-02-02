package services;

import utils.DbContext;

import entities.Category;
import entities.Product;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import program.MainProject;

import java.util.List;

public class ProductService {

    public static void InsertInto(Product product)
    {
        MainProject.session.beginTransaction();
        MainProject.session.save(product);
        MainProject.session.getTransaction().commit();
    }

    public static void Update(int id, Product product)
    {
        MainProject.session.beginTransaction();

        List<Product> products = MainProject.session.createQuery("FROM Product WHERE Id = id").list();

        String newTitle = product.getTitle().length() == 0 ? products.get(0).getTitle() : product.getTitle();
        String newDesc = product.getDescription().length() ==  0 ? products.get(0).getDescription() : product.getDescription();
        int newPrice = product.getPrice() == 0 ? products.get(0).getPrice() : product.getPrice();

        Query query = MainProject.session.createQuery("UPDATE Product SET Title = :productTitle" +
                ", Description = :productDescription"
                +", Price = :productPrice" +" WHERE Id = :id" );

        query.setParameter("productTitle", newTitle);
        query.setParameter("productDescription", newDesc);
        query.setParameter("productPrice", newPrice);
        query.setParameter("id", id);
        query.executeUpdate();
        MainProject.session.getTransaction().commit();
    }

    public static List<Product> Select()
    {
        Query query = MainProject.session.createQuery("FROM Product");
        List<Product> products = query.list();
        return products;
    }

    public static void Delete(int id)
    {
        MainProject.session.beginTransaction();
        Query query = MainProject.session.createQuery("DELETE Product where Id =: id");
        query.setParameter("id", id);
        query.executeUpdate();
        MainProject.session.getTransaction().commit();
    }

    public static void ShowInConsole()
    {
        List<Product> products = Select();
        for (Product product : products)
        {
            System.out.println("--------------------");
            System.out.println("Id: " + product.getId());
            System.out.println("Name: " + product.getTitle());
            System.out.println("--------------------");
        }
    }
}
