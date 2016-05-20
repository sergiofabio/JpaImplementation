package br.com.powercomp.jpaimplementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class JpaConnection {
    
    public static void main(String[] args) {
        
        //contruindo conexao com a base de acordo com o persistence-unit : TestJpaImplementation
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestJpaImplementation");
        EntityManager em = factory.createEntityManager();
        
        try {

            //String sql = "SELECT P FROM People P";

            String sql = "SELECT * FROM PEOPLE";

            em.getTransaction().begin();

            People p  = new People();
            People p1 = new People();
            People p2 = new People();
            People p3 = new People();
            People p4 = new People();

            //p

            p.setAge(40);
            p.setName("Albert Einstein");

            //p1

            p1.setAge(50);
            p1.setName("René Descartes");

            //p2

            p2.setAge(60);
            p2.setName("Galileo Galilei");

            //p3

            p3.setAge(70);
            p3.setName("Johannes Kepler");

            //p4

            p4.setAge(80);
            p4.setName("Isaac Newton");

            //persistencia 

            em.persist(p);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);

            em.getTransaction().commit();


            //construindo query para obter todos os valotres da tabela People

            Query query = em.createNativeQuery(sql, People.class);

            List<People> peopleList = query.getResultList();

            //Java 1.8 lambda expression para imprimir nome e idade 

            peopleList.forEach(people -> {

                System.out.println("Name : " + people.getName() + "\n");
                System.out.println("Age  : " + people.getAge()  + "\n");

            });
            
        }catch(Exception e){
            
            System.out.println("[ERROR: ] " + e.getMessage());
            
        }finally{
            
            em.close();
            factory.close();
        }
    }
    
}
