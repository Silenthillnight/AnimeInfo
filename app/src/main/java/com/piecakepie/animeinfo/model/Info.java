package com.piecakepie.animeinfo.model;

import com.piecakepie.animeinfo.util.InfoConverter;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.util.List;

@Root(strict = false)
@Convert(value = InfoConverter.class)
public class Info {

   private String type;

   // Images
   private List<Img> img;

   // Official website field
   private String href;

   // Text
   private String value;

   public void setHref(String href) {
      this.href = href;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setImg(List<Img> img) {
      this.img = img;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getHref() {
      return href;
   }

   public String getType() {
      return type;
   }

   public List<Img> getImg() {
      return img;
   }

   public String getValue() {
      return value;
   }

   @Override
   public String toString() {
      return "Info{" +
             "type='" + type + '\'' +
             ", img=" + img +
             ", href='" + href + '\'' +
             ", value='" + value + '\'' +
             '}';
   }
}
