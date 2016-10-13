package com.piecakepie.animeinfo.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * This is the root level of the AnimeNewsNetwork response.  Because their api is full of shit and uneasy to use.
 */
@Root(name = "ann", strict = false)
public class Ann {

   @ElementList(entry = "anime", inline = true)
   private List<Anime> anime;

   public List<Anime> getAnime() {
      return anime;
   }

   @Override
   public String toString() {
      return "Ann{" +
             "anime=" + anime +
             '}';
   }
}
