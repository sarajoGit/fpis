/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import com.domain.Narudzbenica;
import com.domain.Status;
import com.domain.StavkeNarudzbenice;
import com.domain.StavkeNarudzbenicePK;
import com.domain.ZahtevZaKatalog;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Sara Jovanovic
 */
public class DBBroker {
    
    private static DBBroker instance;
    private EntityManager em;
    private EntityManagerFactory emf;

    public EntityManager getEm() {
        return em;
    }
    
    public DBBroker()
    {
        emf = Persistence.createEntityManagerFactory("fpis");
    }
    
    public static DBBroker getInstance() {
        if(instance==null) {
            instance = new DBBroker();
        }
        return instance;
    }
    
    public void otvoriKonekciju()
    {
        if (em == null || em.isOpen() == false)
        {
            em = emf.createEntityManager();
        }
    }

    public void zatvoriKonekciju()
    {
        if (em.isOpen())
        {
            em.close();
        }
    }

    public void pokreniTransakciju()
    {
        if (em.isOpen())
        {
            em.getTransaction().begin();
        }
    }

    public void commit()
    {
        if (em.isOpen())
        {
            
            em.getTransaction().commit();
        }
    }

    public void rollback()
    {
        if (em.isOpen())
        {
            em.getTransaction().rollback();
        }
    }

    public Object get(Class c, String id)
    {
        try
        {
            otvoriKonekciju();
            pokreniTransakciju();

            Object o = em.find(c, id);

            commit();
            return o;
        } catch (Exception e)
        {
            e.printStackTrace();

            rollback();
            return null;
        } finally
        {
            zatvoriKonekciju();
        }
    }
    
     public Object get(Class c, int id)
    {
        try
        {
            otvoriKonekciju();
            pokreniTransakciju();

            Object o = em.find(c, id);
            
            
            commit();
            return o;
        } catch (Exception e)
        {
            e.printStackTrace();

            rollback();
            return null;
        } finally
        {
            zatvoriKonekciju();
        }
    }

    public boolean insert(Object o)
    {
        try
        {
            otvoriKonekciju();
            pokreniTransakciju();

            em.persist(o);
            em.flush();

            commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();

            rollback();
            return false;
        } finally
        {
            zatvoriKonekciju();
        }
    }

    public boolean delete(Class c, String id)
    {
        try
        {
            otvoriKonekciju();
            pokreniTransakciju();

            Object o = em.find(c, id);

            if (o != null)
            {
                em.remove(o);
            } else
            {
                return false;
            }

            commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();

            rollback();
            return false;
        } finally
        {
            zatvoriKonekciju();
        }
    }
    
    public boolean delete(Class c, int id)
    {
        try
        {
//            otvoriKonekciju();
//            pokreniTransakciju();

            Object o = em.find(c, id);

            if (o != null)
            {
                em.remove(o);
            } else
            {
                return false;
            }

            //commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();

            //rollback();
            return false;
        } 
    }

    public <T> List<T> vratiSve(Class<T> c, String tabela)
    {
        List<T> l = new ArrayList<>();

        try
        {
            otvoriKonekciju();
            pokreniTransakciju();
            l = em.createNamedQuery(tabela + ".findAll").getResultList();
            commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            rollback();
        } finally
        {
            zatvoriKonekciju();
            return l;
        }
    }

    public boolean azurirajZahtev(ZahtevZaKatalog zzk) {
        try
        {
            ZahtevZaKatalog o = em.find(ZahtevZaKatalog.class, zzk.getBrojKataloga());
            if (o != null)
            {
                o.copy(zzk);
                em.flush();
            }
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<StavkeNarudzbenice> vratiStavke(int brojNarudzbenice) {
        List<StavkeNarudzbenice> sn = new ArrayList<>();

        try
        {
            otvoriKonekciju();
            pokreniTransakciju();

            Query upit = em.createNamedQuery("StavkeNarudzbenice.findByBrojNarudzbenice");
            upit.setParameter("brojNarudzbenice", brojNarudzbenice);
            sn = upit.getResultList();

            commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            rollback();
        } finally
        {
            zatvoriKonekciju();
            return sn;
        }
    }
    
    public StavkeNarudzbenice vratiStavku(int rbStavke, int brojNarudzbenice)
    {
        StavkeNarudzbenice s = new StavkeNarudzbenice();

        try
        {
            otvoriKonekciju();
            pokreniTransakciju();

            s = (StavkeNarudzbenice) em.find(StavkeNarudzbenice.class, new StavkeNarudzbenicePK(rbStavke, brojNarudzbenice));

            commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            rollback();
        } finally
        {
            zatvoriKonekciju();
            return s;
        }
    }

    public boolean dodajNarudzbenicu(Narudzbenica nar) {
        
         try
        {
            ArrayList<StavkeNarudzbenice> stavke = new ArrayList<StavkeNarudzbenice>();
            stavke.addAll(nar.getStavkeNarudzbeniceCollection());

            nar.getStavkeNarudzbeniceCollection().clear();
            em.persist(nar);
            em.flush();
            
            for (StavkeNarudzbenice s: stavke)
            {
                s.setStavkeNarudzbenicePK(new StavkeNarudzbenicePK(vratiMaxRB(nar.getBrojNarudzbenice()), nar.getBrojNarudzbenice()));
               // System.out.println("stavke pk "+s.getStavkeNarudzbenicePK());
                em.persist(s);
                em.flush();
            }
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        } 
         
    }

    public boolean azurirajNarudzbenicu(Narudzbenica nar) {
        
        try
        {
            Narudzbenica o = em.find(Narudzbenica.class, nar.getBrojNarudzbenice());
            
            if (o != null)
            {
                o.copy(nar);
            }
            
            for (StavkeNarudzbenice s : nar.getStavkeNarudzbeniceCollection())
            {
                
                StavkeNarudzbenice st = em.find(StavkeNarudzbenice.class, s.getStavkeNarudzbenicePK());
                System.out.println("Stavka u brokeru: "+st);
                if (st == null || st.getStavkeNarudzbenicePK().getRbStavke()==0)
                {
                    System.out.println("Nasao je stavku koja ne postoji u bazi a ima je u ovoj prosledjenoj listi." + s.getStavkeNarudzbenicePK());
                  
                    s.setStavkeNarudzbenicePK(new StavkeNarudzbenicePK(vratiMaxRB(nar.getBrojNarudzbenice()), nar.getBrojNarudzbenice()));
                    em.persist(s);
                    
                } else if (s.getStatus().equals(Status.DELETE))
                {
                    em.remove(st); 
                } else {
                    st.copy(s);
                }
            }
            
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        } 
        
    }
    
    public int vratiMaxRB(int br) {
       
        int max = 1;
        List<StavkeNarudzbenice> sn = new ArrayList<>();
        
        Query upit = em.createNamedQuery("StavkeNarudzbenice.findByBrojNarudzbenice");
        upit.setParameter("brojNarudzbenice", br);
        sn = upit.getResultList();
        
        for (StavkeNarudzbenice stavke : sn) {
             max=(stavke.getStavkeNarudzbenicePK().getRbStavke())+1;
        }
        System.out.println("Max je "+max);
        return max;
    }
}
