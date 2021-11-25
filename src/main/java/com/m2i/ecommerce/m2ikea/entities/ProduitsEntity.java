package com.m2i.ecommerce.m2ikea.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "produits", schema = "ecommerce", catalog = "")
public class ProduitsEntity {
    private int idProduit;
    private String nomProduit;
    private int quantite;
    private BigDecimal prixUnitaire;
    private int unitesStock;
    private int unitesCommandees;
    private BigInteger indisponible;
    private String description;
    private CategoriesEntity codeCategorie;

    @Id
    @Column(name = "id_produit")
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Basic
    @Column(name = "nom_produit")
    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    @Basic
    @Column(name = "quantite")
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Basic
    @Column(name = "prix_unitaire")
    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Basic
    @Column(name = "unites_stock")
    public int getUnitesStock() {
        return unitesStock;
    }

    public void setUnitesStock(int unitesStock) {
        this.unitesStock = unitesStock;
    }

    @Basic
    @Column(name = "unites_commandees")
    public int getUnitesCommandees() {
        return unitesCommandees;
    }

    public void setUnitesCommandees(int unitesCommandees) {
        this.unitesCommandees = unitesCommandees;
    }

    @Basic
    @Column(name = "indisponible")
    public BigInteger getIndisponible() {
        return indisponible;
    }

    public void setIndisponible(BigInteger indisponible) {
        this.indisponible = indisponible;
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
        ProduitsEntity that = (ProduitsEntity) o;
        return idProduit == that.idProduit && quantite == that.quantite && unitesStock == that.unitesStock && unitesCommandees == that.unitesCommandees && Objects.equals(nomProduit, that.nomProduit) && Objects.equals(prixUnitaire, that.prixUnitaire) && Objects.equals(indisponible, that.indisponible) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduit, nomProduit, quantite, prixUnitaire, unitesStock, unitesCommandees, indisponible, description);
    }

    @OneToOne
    @JoinColumn(name = "code_categorie", referencedColumnName = "id_categorie", nullable = false)
    public CategoriesEntity getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(CategoriesEntity codeCategorie) {
        this.codeCategorie = codeCategorie;
    }
}
