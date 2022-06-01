package webapp.db;

import webapp.beansCollection.PointBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;


@Stateless
public class EJB_Connector {

    @PersistenceContext(unitName = "namePU")
    private EntityManager entityManager;

    public boolean addEntity(double x, double y, double r, boolean hit){
        PointEntity pointEntity = new PointEntity();
        pointEntity.setId();
        pointEntity.setX(x);
        pointEntity.setY(y);
        pointEntity.setR(r);
        pointEntity.setHit(hit);
        pointEntity.setDateCreation(new Date());
        entityManager.persist(pointEntity);
        return true;
    }

    public  List<PointBean>  updateList(){
        List<PointEntity> pointBeansId = entityManager.createQuery("select entity from PointEntity entity").getResultList();
        IdGenerator.clear();
        for (PointEntity points: pointBeansId) {
            IdGenerator.getIdSet().add(Integer.valueOf(points.getId()));
        }
        return entityManager.createQuery("select entity from PointEntity entity").getResultList();
    }


    public boolean cleanTable(){
        return entityManager.createQuery("DELETE FROM PointEntity entity").executeUpdate() > 0;
    }
}
