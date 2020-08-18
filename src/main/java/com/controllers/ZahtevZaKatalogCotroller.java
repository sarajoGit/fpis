/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.db.DBBroker;
import com.domain.Dobavljac;
import com.domain.Grad;
import com.domain.ZahtevZaKatalog;
import com.domain.Zaposleni;
import java.text.ParseException;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sara Jovanovic
 */

@Controller
@RequestMapping("/zahtev")
public class ZahtevZaKatalogCotroller {
    
    
    @RequestMapping(value = "/prikaz", method = RequestMethod.GET)
    public ModelAndView prikazGet() throws ParseException
    {
        List<ZahtevZaKatalog> zzk = DBBroker.getInstance().vratiSve(ZahtevZaKatalog.class, "ZahtevZaKatalog");

        ModelAndView mv = new ModelAndView("zahtev/prikaz");
        mv.addObject("zahtevi", zzk);

        return mv;
    }
    
    @RequestMapping(value = "/dodaj", method = RequestMethod.GET)
    public ModelAndView dodajGet() throws ParseException
    {
        List<Zaposleni> zaposleni  = DBBroker.getInstance().vratiSve(Zaposleni.class, "Zaposleni");
        List<Dobavljac> dobavljaci  = DBBroker.getInstance().vratiSve(Dobavljac.class, "Dobavljac");
        
        ModelAndView mv= new ModelAndView("zahtev/dodaj");
        mv.addObject("zahtev", new ZahtevZaKatalog());
        mv.addObject("zaposleni", zaposleni);
        mv.addObject("dobavljaci", dobavljaci);
        
       
        return mv;
    }
    
    @RequestMapping(value = "/dodaj", method = RequestMethod.POST)
    public String postDodaj(@ModelAttribute("zahtev") ZahtevZaKatalog zzk) throws ParseException
    {
        DBBroker.getInstance().insert(zzk);
        return "redirect:/zahtev/prikaz";
    }
    
    
    @RequestMapping(value = "/azuriraj/{id}", method = RequestMethod.GET)
    public ModelAndView getAzuriraj(@PathVariable int id) throws ParseException
    {
        ModelAndView mv = new ModelAndView("zahtev/azuriraj");
        
        Object o = DBBroker.getInstance().get(ZahtevZaKatalog.class, id);
        
        List<Zaposleni> zaposleni  = DBBroker.getInstance().vratiSve(Zaposleni.class, "Zaposleni");
        List<Dobavljac> dobavljaci  = DBBroker.getInstance().vratiSve(Dobavljac.class, "Dobavljac");
        
        ZahtevZaKatalog zzk = null;
        
        if (o != null){
            zzk = (ZahtevZaKatalog) o;}
        
        mv.addObject("zahtev", zzk);
        mv.addObject("zaposleni", zaposleni);
        mv.addObject("dobavljaci", dobavljaci);
        
        return mv;
    }
    
    @RequestMapping(value = "/azuriraj", method = RequestMethod.POST)
    public String postAzuriraj(@ModelAttribute("zahtev") ZahtevZaKatalog zzk) throws ParseException
    {
        
        DBBroker.getInstance().otvoriKonekciju();
        DBBroker.getInstance().pokreniTransakciju();
        
        if(DBBroker.getInstance().azurirajZahtev(zzk) == true){
            DBBroker.getInstance().commit();
        } else{
            DBBroker.getInstance().rollback();
        }
        
        DBBroker.getInstance().zatvoriKonekciju();
        
        return "redirect:/zahtev/prikaz";
    }
    
    @RequestMapping(value = "/obrisi/{id}", method = RequestMethod.GET, headers="Accept=*/*", produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean getObrisi(@PathVariable int id) throws ParseException
    {
        DBBroker.getInstance().otvoriKonekciju();
        DBBroker.getInstance().pokreniTransakciju();
        boolean b = DBBroker.getInstance().delete(ZahtevZaKatalog.class, id);
        
        if(b==true){
            //DBBroker.getInstance().delete(ZahtevZaKatalog.class, id);
            DBBroker.getInstance().commit();
        } else{
            DBBroker.getInstance().rollback();
        }
        
        DBBroker.getInstance().zatvoriKonekciju();
        
        return b;
    }
}
