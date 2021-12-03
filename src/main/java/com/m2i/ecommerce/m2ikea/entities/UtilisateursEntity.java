package com.m2i.ecommerce.m2ikea.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "utilisateurs", schema = "ecommerce", catalog = "")
public class UtilisateursEntity {
    private int idUtilisateur;
    private String nomUtilisateur;
    private String email;
    private String motdepasse;
    private String role;
    private String images;
    private ClientsEntity client;

    public UtilisateursEntity() {

    }

    public UtilisateursEntity(String nomUtilisateur, String email, String motdepasse, String role) {
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
        this.motdepasse = motdepasse;
        this.role = role;
    }

    public UtilisateursEntity(String nomUtilisateur, String email, String motdepasse, String role, String images) {
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
        this.motdepasse = motdepasse;
        this.role = role;
        this.images = images;
    }

    @Id
    @Column(name = "id_utilisateur")
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Basic
    @Column(name = "nom_utilisateur")
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "motdepasse")
    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "images")
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateursEntity that = (UtilisateursEntity) o;
        return idUtilisateur == that.idUtilisateur && Objects.equals(nomUtilisateur, that.nomUtilisateur) && Objects.equals(email, that.email) && Objects.equals(motdepasse, that.motdepasse) && Objects.equals(role, that.role) && Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, nomUtilisateur, email, motdepasse, role, images);
    }

    @OneToOne
    @JoinColumn(name = "client", referencedColumnName = "id_client", nullable = true)
    public ClientsEntity getClient() {
        return client;
    }

    public void setClient(ClientsEntity client) {
        this.client = client;
    }
}
