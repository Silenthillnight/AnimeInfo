package com.piecakepie.animeinfo.dto.network;

import android.support.annotation.NonNull;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Stores the actual information from the ann query.  Since the xml is formatted all janky, won't be strict on it.
 */
@Root(strict = false)
public class Anime {

   @Attribute
   private int id;

   @Attribute
   private String type;

   @Attribute
   private String name;

   @ElementList(inline = true)
   private List<Info> info;

   @ElementList(inline = true)
   private List<Credit> credit;

   public int getId() {
      return id;
   }

   public String getType() {
      return type;
   }

   public String getName() {
      return name;
   }

   public List<Info> getInfo() {
      return info;
   }

   public List<Credit> getCredit() {
      return credit;
   }

   @Override
   public String toString() {
      return "Anime{" +
             "id=" + id +
             ", type='" + type + '\'' +
             ", name='" + name + '\'' +
             ", info=" + info +
             ", credit=" + credit +
             '}';
   }
}
