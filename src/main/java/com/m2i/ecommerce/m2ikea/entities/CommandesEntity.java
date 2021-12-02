package com.m2i.ecommerce.m2ikea.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "commandes", schema = "ecommerce", catalog = "")
public class CommandesEntity {
    private int idCommande;
    private Timestamp dateCommande;
    private Timestamp dateEnvoi;
    private float port;
    private ClientsEntity client;

    public CommandesEntity(int idCommande, Timestamp dateCommande, Timestamp dateEnvoi, float port, ClientsEntity client) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.dateEnvoi = dateEnvoi;
        this.port = port;
        this.client = client;
    }

    public CommandesEntity() {
    }

    @Id
    @Column(name = "id_commande")
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Basic
    @Column(name = "date_commande")
    public Timestamp getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Timestamp dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Basic
    @Column(name = "date_envoi")
    public Timestamp getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Timestamp dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Basic
    @Column(name = "port")
    public float getPort() {
        return port;
    }

    public void setPort(float port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandesEntity that = (CommandesEntity) o;
        return idCommande == that.idCommande && Objects.equals(dateCommande, that.dateCommande) && Objects.equals(dateEnvoi, that.dateEnvoi) && Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, dateCommande, dateEnvoi, port);
    }

    @OneToOne
    @JoinColumn(name = "client", referencedColumnName = "id_client", nullable = false)
    public ClientsEntity getClient() {
        return client;
    }

    public void setClient(ClientsEntity client) {
        this.client = client;
    }
}
