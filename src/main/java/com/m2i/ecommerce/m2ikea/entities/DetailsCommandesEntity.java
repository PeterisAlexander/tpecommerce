package com.m2i.ecommerce.m2ikea.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "details_commandes", schema = "ecommerce", catalog = "")
public class DetailsCommandesEntity {
    private int idCommande;
    private BigDecimal prixUnitaire;
    private int quantite;
    private double remise;
    private ProduitsEntity produit;

    @Id
    @Column(name = "id_commande")
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
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
    @Column(name = "quantite")
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Basic
    @Column(name = "remise")
    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsCommandesEntity that = (DetailsCommandesEntity) o;
        return idCommande == that.idCommande && quantite == that.quantite && Double.compare(that.remise, remise) == 0 && Objects.equals(prixUnitaire, that.prixUnitaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, prixUnitaire, quantite, remise);
    }

    @OneToOne
    @JoinColumn(name = "produit", referencedColumnName = "id_produit", nullable = false)
    public ProduitsEntity getProduit() {
        return produit;
    }

    public void setProduit(ProduitsEntity produit) {
        this.produit = produit;
    }
}
