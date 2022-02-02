package services;

import program.MainProject;
import utils.DbContext;
import entities.Category;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryService {
    public static void InsertInto(Category category)
    {
        MainProject.session.beginTransaction();
        MainProject.session.save(category);
        MainProject.session.getTransaction().commit();
    }

    public static void Update(int id, Category category)
    {
        MainProject.session.beginTransaction();

        List<Category> categories = MainProject.session.createQuery("FROM Category WHERE Id = id").list();

        String newName = (category.getName() == null || category.getName().length() == 0) ?
                categories.get(0).getName() : category.getName();

        Query query = MainProject.session.createQuery("UPDATE Category SET Name = :categoryName" +
                " WHERE Id = :categoryId");
        query.setParameter("categoryName", newName);
        query.setParameter("categoryId", id);
        query.executeUpdate();
        MainProject.session.getTransaction().commit();
    }

    public static List<Category> Select()
    {
        Query query = MainProject.session.createQuery("FROM Category");
        List<Category> categories = query.list();
        return categories;
    }

    public static void Delete(int id)
    {
        MainProject.session.beginTransaction();
        Query query = MainProject.session.createQuery("DELETE Category where Id =: id");
        query.setParameter("id", id);
        query.executeUpdate();
        MainProject.session.getTransaction().commit();
    }

    public static void ShowInConsole()
    {
        List<Category> categories = Select();
        for (Category cat : categories)
        {
            System.out.println("--------------------");
            System.out.println("Id: " + cat.getId());
            System.out.println("Name: " + cat.getName());
            System.out.println("--------------------");
        }
    }
}
