/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.db.DBBroker;
import com.domain.Narudzbenica;
import com.domain.Proizvod;
import com.domain.RokIsporuke;
import com.domain.Status;
import com.domain.StavkeNarudzbenice;
import com.domain.StavkeNarudzbenicePK;

import com.domain.Zaposleni;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pomocna.StavkaZaPrikaz;

/**
 *
 * @author Sara Jovanovic
 */

@Controller
@Scope("session")
@RequestMapping(value = "/narudzbenica")
public class NarudzbenicaController implements Serializable{
    
    Narudzbenica nar;

    
    @RequestMapping(value = "/prikaz", method = RequestMethod.GET)
    public ModelAndView prikazGet() throws ParseException
    {
        
        List<Narudzbenica> narudzbenice = DBBroker.getInstance().vratiSve(Narudzbenica.class, "Narudzbenica");
        ModelAndView mv = new ModelAndView("narudzbenica/prikaz");
        mv.addObject("narudzbenice", narudzbenice);

        return mv;
    }
    
    @RequestMapping(value = "/dodaj", method = RequestMethod.GET)
    public ModelAndView dodajGet() throws ParseException
    {
        
        int suma = 0;
        List<Zaposleni> zaposleni = DBBroker.getInstance().vratiSve(Zaposleni.class, "Zaposleni");
        List<RokIsporuke> rokovi = DBBroker.getInstance().vratiSve(RokIsporuke.class, "RokIsporuke");
        List<Proizvod> proizvodi = DBBroker.getInstance().vratiSve(Proizvod.class, "Proizvod");

        if (nar == null || nar.getBrojNarudzbenice() != null) 
        {
            System.out.println("Kontroler narudzbenice je napravio novu narudzbenicu.");
            nar = new Narudzbenica();
        }
        
        for (StavkeNarudzbenice sn : nar.getStavkeNarudzbeniceCollection()) {
            System.out.println("Stavke nar sa forme: "+sn.getStavkeNarudzbenicePK() +" kolicine "+sn.getKolicina());
            suma += Integer.parseInt(sn.getKolicina());
        }
        System.out.println("Ukupno: "+suma);
        nar.setUkupnoNaruceno(suma+"");
        
        
        ModelAndView mv = new ModelAndView("narudzbenica/azuriraj");
        mv.addObject("narudzbenica", nar);
        mv.addObject("rokovi", rokovi);
        mv.addObject("zaposleni", zaposleni);
        mv.addObject("proizvodi", proizvodi);
        mv.addObject("stavke", nar.getStavkeNarudzbeniceCollection());

        StavkeNarudzbenice snar = new StavkeNarudzbenice();
        
        mv.addObject("stavka", snar);
        return mv;
    }
    
    
    
    @RequestMapping(value = "/azuriraj", method = RequestMethod.POST)
    public String azuriraj(@ModelAttribute("narudzbenica") Narudzbenica narudzbenica) throws ParseException
    {
          if (narudzbenica.getBrojNarudzbenice()== null) //dodavanje
        {
            nar.copy(narudzbenica);
            System.out.println(nar.getBrojNarudzbenice());
            return "redirect:/narudzbenica/dodaj/"; 
        } 
          else //azuriranje
        {
            nar.copy(narudzbenica);
            return "redirect:/narudzbenica/azuriraj/" + narudzbenica.getBrojNarudzbenice(); // GET azuriraj/{id}
        }

    }
    
@RequestMapping(value = "/azuriraj/{id}", method = RequestMethod.GET)
    public ModelAndView azurirajGetId(@PathVariable int id) throws ParseException
    {
        int suma = 0;
        int sumaAzurirajStavku = 0;
        int razlikaObrisi = 0;
        List<Zaposleni> zaposleni = DBBroker.getInstance().vratiSve(Zaposleni.class, "Zaposleni");
        List<RokIsporuke> rokovi = DBBroker.getInstance().vratiSve(RokIsporuke.class, "RokIsporuke");
        List<Proizvod> proizvodi = DBBroker.getInstance().vratiSve(Proizvod.class, "Proizvod");

        if (nar == null || nar.getBrojNarudzbenice()== null) //provera ukoliko nisam potvrdila transakciju
        {
            System.out.println("Sad ce da napravi novu nar iz baze.");
            nar = (Narudzbenica) DBBroker.getInstance().get(Narudzbenica.class, id);
            for(StavkeNarudzbenice sn: nar.getStavkeNarudzbeniceCollection()) {
                System.out.println("iz narudzbenice na formi: " + sn.getStavkeNarudzbenicePK());
                
            }
            nar.getStavkeNarudzbeniceCollection().clear();
            List<StavkeNarudzbenice> stavkeizBaze = DBBroker.getInstance().vratiStavke(id);
            for(StavkeNarudzbenice s: stavkeizBaze) {
                System.out.println("stavke iz baze: "+s.getStavkeNarudzbenicePK());
                suma += Integer.parseInt(s.getKolicina());
                System.out.println(suma);
            }
            
            nar.getStavkeNarudzbeniceCollection().addAll(stavkeizBaze);
        }
        
        if(suma!=0){
            nar.setUkupnoNaruceno(suma+"");
        }
        
        for (StavkeNarudzbenice sn : nar.getStavkeNarudzbeniceCollection()) {
            if(sn.getStatus().equals(Status.DELETE)){
                razlikaObrisi = Integer.parseInt(nar.getUkupnoNaruceno());
                nar.setUkupnoNaruceno(razlikaObrisi+"");
            } else{
                sumaAzurirajStavku+= Integer.parseInt(sn.getKolicina());
                nar.setUkupnoNaruceno(sumaAzurirajStavku+"");
            }
        }
        
        ModelAndView mv = new ModelAndView("narudzbenica/azuriraj");
        mv.addObject("narudzbenica", nar);
        mv.addObject("rokovi", rokovi);
        mv.addObject("zaposleni", zaposleni);
        mv.addObject("proizvodi", proizvodi);
        mv.addObject("stavke", nar.getStavkeNarudzbeniceCollection());

        
        StavkeNarudzbenice snar = new StavkeNarudzbenice();
        snar.setStavkeNarudzbenicePK(new StavkeNarudzbenicePK(0, nar.getBrojNarudzbenice()));
        mv.addObject("stavka", snar);

        return mv;
    }
    
    @RequestMapping(value = "/dodajStavku", method = RequestMethod.POST)
    public String dodajStavku(@ModelAttribute("stavka") StavkaZaPrikaz sn) throws ParseException
    {
        
        int suma = 0;
        sn.setSifraProizvoda((Proizvod) DBBroker.getInstance().get(Proizvod.class, sn.getSifraProizvoda().getSifraProizvoda()));
        
        
        for(StavkeNarudzbenice s: nar.getStavkeNarudzbeniceCollection()) {
            
            suma += Integer.parseInt(s.getKolicina());
            nar.setUkupnoNaruceno(suma+"");
            
        }
        
        if(nar.getBrojNarudzbenice()!=null){
            nar.dodajStavku(sn, nar.getBrojNarudzbenice());
        } else{
            nar.dodajStavku(sn, 0);
        }
         
        if (nar.getBrojNarudzbenice()== null)
        {
            return "redirect:/narudzbenica/dodaj/";
        } else
        {
            return "redirect:/narudzbenica/azuriraj/" + nar.getBrojNarudzbenice();
        }
    }
    
    //prima zahtev i otvara stranu za azuriranje
    @RequestMapping(value = "/azurirajStavku/{rbStavke}&{brojNarudzbenice}", method = RequestMethod.GET)
    public ModelAndView azurirajStavkuGet(@PathVariable int rbStavke, @PathVariable int brojNarudzbenice) throws ParseException
    {   
       
        StavkeNarudzbenice s = DBBroker.getInstance().vratiStavku(rbStavke, brojNarudzbenice);
        List<Proizvod> proizvodi = DBBroker.getInstance().vratiSve(Proizvod.class, "Proizvod");
        
        ModelAndView mv = new ModelAndView("stavkaNarudzbenice/azuriraj");
        mv.addObject("stavka", s);
        mv.addObject("proizvodi", proizvodi);
        return mv;
    }
    
     //azurira stavku koja je vec u bazi
    @RequestMapping(value = "/azurirajStavku", method = RequestMethod.POST)
    public String azurirajStavkuPost(@ModelAttribute StavkaZaPrikaz s) throws ParseException
    {
        if(s==null) {
            System.out.println("Stavka koja treba da bude azurirana je null.");
        }
        
        for (StavkeNarudzbenice sn : nar.getStavkeNarudzbeniceCollection()) {
            
            if(sn.getOpisStavke().trim().equals(s.getOpisStavke().trim()) && sn.getSifraProizvoda().getSifraProizvoda().equals(s.getSifraProizvoda().getSifraProizvoda()) && !sn.getStatus().equals(Status.UPDATENEW)){
                s.setRbStavke(sn.getStavkeNarudzbenicePK().getRbStavke());
                break;
            }
            
            if(sn.getStatus().equals(Status.UPDATENEW)){
                s.setStatus(Status.UPDATENEW);
            }
        }

        nar.azurirajStavku(s, nar.getBrojNarudzbenice());
        return "redirect:/narudzbenica/azuriraj/" + nar.getBrojNarudzbenice();

    }
    
    // vraca stranu za azuriranje stavke koja nije u bazi
    @RequestMapping(value = "/azurirajNovuStavku", method = RequestMethod.GET)
    public ModelAndView azurirajNovuStavkuGet() throws ParseException
    {
        List<Proizvod> proizvodi = DBBroker.getInstance().vratiSve(Proizvod.class, "Proizvod");

        StavkeNarudzbenice stNewUpdate = new StavkeNarudzbenice();
        for(StavkeNarudzbenice s: nar.getStavkeNarudzbeniceCollection()) {
            if(s.getStatus().equals(Status.UPDATENEW)) {
                stNewUpdate = s;
                break;
            }
        }
        
        ModelAndView mv = new ModelAndView("stavkaNarudzbenice/azuriraj");
        mv.addObject("stavka", stNewUpdate);
        mv.addObject("proizvodi", proizvodi);
        
        return mv;
    }
    
    //azuriraj dugme za novu stavku POST metoda
    @RequestMapping(value = "/azurirajNovuStavku", method = RequestMethod.POST)
    public String azurirajNovuStavkuPost(@ModelAttribute StavkaZaPrikaz s) throws ParseException
    {
        List<Proizvod> proizvodi = DBBroker.getInstance().vratiSve(Proizvod.class, "Proizvod");
        
        String kolicina = s.getKolicina();
        System.out.println("Azurirana kolicina stavke je: "+kolicina);
        nar.azurirajStavku(s, 0);
        nar.setUkupnoNaruceno(nar.getUkupnoNaruceno()+kolicina);
        
        return "redirect:/narudzbenica/dodaj/";
    }
    
    
    //prima zahtev za azuriranje stavke koja nije u bazi 
    @RequestMapping(value = "/azurirajNovuStavkuStrana", method = RequestMethod.POST)
    public String posaljiPodatkeZaStavku(HttpServletRequest request) throws ParseException
    {
        
        String opisStavke = request.getParameter("OpisStavke");
        String kolicina = request.getParameter("Kolicina");
        String sifraProizvoda = request.getParameter("SifraProizvoda");
        
        System.out.println(opisStavke + kolicina);
        
        if(nar==null) {
            System.out.println("Narudzbenica je null u azuriraj novu stavku strana \n");
        }
        
        for (StavkeNarudzbenice st : nar.getStavkeNarudzbeniceCollection())
        {
            if (st.getOpisStavke().equals(opisStavke)  &&  st.getKolicina().equals(kolicina))
            {
                //izmenjena stavka ali nova
                st.setStatus(Status.UPDATENEW);
                System.out.println("Za ovu stavku sto menjam podaci i status u nsGET: " + st.getOpisStavke()+ st.getKolicina()+ st.getStatus());
                break;
            }
        }
        return "redirect:/narudzbenica/azurirajNovuStavku"; 
    }
    
    // brisanje vec postojece stavke (koja postoji u bazi, ali ne brise odmah sa forme - brise iz baze tek kad se potvrdi transakcija)
    @RequestMapping(value = "/obrisiStavku/{rbStavke}&{brojNarudzbenice}", method = RequestMethod.GET, headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    boolean getObrisi(@PathVariable int rbStavke, @PathVariable int brojNarudzbenice) throws ParseException
    {
        
        Narudzbenica n = (Narudzbenica) DBBroker.getInstance().get(Narudzbenica.class, brojNarudzbenice);
        
        if (n.getStavkeNarudzbeniceCollection().size() == 1)
        {
            return false;
        }
        StavkeNarudzbenicePK pk = new StavkeNarudzbenicePK(rbStavke, brojNarudzbenice);
        System.out.println("PK ove sto treba da bude izbrisana iz baze: "+pk);
        
        for (StavkeNarudzbenice s : nar.getStavkeNarudzbeniceCollection())
        {
            if (s.getStavkeNarudzbenicePK().equals(pk))
            {
                s.setStatus(Status.DELETE);
                return true;
            }
        }
        return false;
    }
    
    
    @RequestMapping(value = "/obrisiNovuStavku", method = RequestMethod.POST)
    public String obrisiNovuStavkuPost(HttpServletRequest request) throws ParseException
    {
        //izvlacim sve podatke za stavku
        String opisStavke = request.getParameter("OpisStavke");
        String kolicina = request.getParameter("Kolicina");
        System.out.println("ObrisiNS: opis "+opisStavke+" Kolicina "+kolicina);

        for (StavkeNarudzbenice st : nar.getStavkeNarudzbeniceCollection())
        {
            
            //uporedjujem sve podatke sa onim sto su vec u listi
            if (st.getOpisStavke().equals(opisStavke)  &&  st.getKolicina().equals(kolicina))
            {
                int ukNaruceno = Integer.parseInt(nar.getUkupnoNaruceno());
                int razlika = ukNaruceno - Integer.parseInt(st.getKolicina());
                nar.getStavkeNarudzbeniceCollection().remove(st);
                System.out.println("Ukupno naruceno u obrisiNovuStavku: "+razlika);
                nar.setUkupnoNaruceno(razlika+"");
                break;
            }
        }

        if (nar.getBrojNarudzbenice()== null)
        {
            return "redirect:/narudzbenica/dodaj/";
        } else
        {
            return "redirect:/narudzbenica/azuriraj/" + nar.getBrojNarudzbenice();
        }
    }
    
    @RequestMapping(value = "/obrisi/{id}", method = RequestMethod.GET,  headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean obrisi(@PathVariable int id) throws ParseException
    {
        
        DBBroker.getInstance().otvoriKonekciju();
        DBBroker.getInstance().pokreniTransakciju();
        
        boolean b = DBBroker.getInstance().delete(Narudzbenica.class, id);
        
        if(b==true){
            DBBroker.getInstance().commit();
        } else{
            DBBroker.getInstance().rollback();
        }
        
        DBBroker.getInstance().zatvoriKonekciju();
        return b;
    }
    
    
    
    @RequestMapping(value = "/potvrdiTransakciju", method = RequestMethod.GET)
    public @ResponseBody
    boolean dodajStavku() throws ParseException
    {
        System.out.println("/potvrdiTransakciju");
        
        if (nar == null)
        {
            return false;
        } else
        {
            
            DBBroker.getInstance().otvoriKonekciju();
            DBBroker.getInstance().pokreniTransakciju();
            
            if (nar.getBrojNarudzbenice()== null) 
            {
                if (nar.getUkupnoNaruceno()== null)
                {
                    return false;
                    
                } else
                {
                    //dodavanje
                    if(DBBroker.getInstance().dodajNarudzbenicu(nar) == true){
                        DBBroker.getInstance().commit();
                        DBBroker.getInstance().zatvoriKonekciju();
                        nar = null;
                        return true;
                        
                    } else{
                        DBBroker.getInstance().rollback();
                        DBBroker.getInstance().zatvoriKonekciju();
                        nar = null;
                        return false;
                    }
                }

            } else
            {
                // azuriranje
                if(DBBroker.getInstance().azurirajNarudzbenicu(nar) == true){
                    DBBroker.getInstance().commit();
                    DBBroker.getInstance().zatvoriKonekciju();
                    nar = null;
                    return true;
                    
                } else{
                    DBBroker.getInstance().getEm().flush();
                    DBBroker.getInstance().rollback();
                    DBBroker.getInstance().zatvoriKonekciju();
                    nar = null;
                    return false;
                }
                
            }
        }
    }
}
