package com.m2i.ecommerce.m2ikea.repositories;
import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommandesRepository extends CrudRepository<CommandesEntity, Integer> {

        Iterable<CommandesEntity> findAll();
        Iterable<CommandesEntity> findByIdCommandeContains(String search);

        void deleteById(int id);
    //*****************date commande************
    /*@Query( value="SELECT * FROM commandes WHERE date(dateCommande) = ?1", nativeQuery=true )
    public Iterable<CommandesEntity> findByCustomByDateCommande(Date dh); // , Date rdvDate

    @Query( value="SELECT * FROM commandes WHERE client = ?1 AND  date(dateCommande) = ?2", nativeQuery=true )
    public Iterable<CommandesEntity> findByCustomByDateCommandeEtClient(int patientId , Date dh); // , Date rdvDate

    @Query(value=" SELECT month(dateCommande) as mois , year(dateCommande) as annee , count(*) as nbre FROM commandes GROUP BY month(dateCommande) , year(dateCommande)", nativeQuery=true)
    List<Object> getCommandesStats();*/


    //****************date envoi***************************
    /*@Query( value="SELECT * FROM commandes WHERE date(dateEnvoi) = ?1", nativeQuery=true )
    public Iterable<CommandesEntity> findByCustomByDate(Date dh); // , Date rdvDate

    @Query( value="SELECT * FROM commandes WHERE date_Envoi = ?1 AND  date(dateEnvoi) = ?2", nativeQuery=true )
    public Iterable<CommandesEntity> findByCustomByDateEtPatient(int patientId , Date dh); // , Date rdvDate

    @Query(value=" SELECT month(dateEnvoi) as mois , year(dateEnvoi) as annee , count(*) as nbre FROM commandes GROUP BY month(dateEnvoi) , year(dateEnvoi)", nativeQuery=true)
    List<Object> getCommandesStats();
    */
}
