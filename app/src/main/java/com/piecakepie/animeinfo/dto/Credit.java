package com.piecakepie.animeinfo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Credit {

   @Element
   private String task;

   @Element
   private String company;

   public String getTask() {
      return task;
   }

   public void setTask(String task) {
      this.task = task;
   }

   public String getCompany() {
      return company;
   }

   public void setCompany(String company) {
      this.company = company;
   }
}
