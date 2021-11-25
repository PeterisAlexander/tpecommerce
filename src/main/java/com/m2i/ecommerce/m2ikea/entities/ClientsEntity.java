package com.m2i.ecommerce.m2ikea.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "ecommerce", catalog = "")
public class ClientsEntity {
    private int idClient;
    private String nomClient;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pays;
    private String telephone;

    @Id
    @Column(name = "id_client")
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "nom_client")
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    @Basic
    @Column(name = "adresse")
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "ville")
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "code_postal")
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Basic
    @Column(name = "pays")
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsEntity that = (ClientsEntity) o;
        return idClient == that.idClient && Objects.equals(nomClient, that.nomClient) && Objects.equals(adresse, that.adresse) && Objects.equals(ville, that.ville) && Objects.equals(codePostal, that.codePostal) && Objects.equals(pays, that.pays) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, nomClient, adresse, ville, codePostal, pays, telephone);
    }
}
