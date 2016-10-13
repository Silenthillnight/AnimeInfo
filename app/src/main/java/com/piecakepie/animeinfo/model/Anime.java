package com.piecakepie.animeinfo.model;

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

   @Override
   public String toString() {
      return "Anime{" +
             "id=" + id +
             ", type='" + type + '\'' +
             ", name='" + name + '\'' +
             ", info=" + info +
             '}';
   }
}
