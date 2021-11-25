package com.m2i.ecommerce.m2ikea.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "ecommerce", catalog = "")
public class CategoriesEntity {
    private int idCategorie;
    private String nomCategorie;
    private String description;

    @Id
    @Column(name = "id_categorie")
    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Basic
    @Column(name = "nom_categorie")
    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        return idCategorie == that.idCategorie && Objects.equals(nomCategorie, that.nomCategorie) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie, nomCategorie, description);
    }
}
