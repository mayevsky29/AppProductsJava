package entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tblProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 200, nullable = false)
    private String Title;
    @Column(nullable = false)
    private String Description;
    @Column(nullable = false)
    private int Price;
    @ManyToOne
    @JoinColumn(name = "Category_Id", nullable = false)
    private Category category;
}
