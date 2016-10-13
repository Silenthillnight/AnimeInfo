package com.piecakepie.animeinfo.util;

import com.piecakepie.animeinfo.model.Img;
import com.piecakepie.animeinfo.model.Info;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.List;

public class InfoConverter implements Converter<Info> {
   @Override
   public Info read(InputNode node) throws Exception {
      Info info = new Info();
      List<Img> imgItems = new ArrayList<>();
      String value = null;
      final InputNode type = node.getAttribute("type");
      final InputNode href = node.getAttribute("href");
      info.setType(type != null ? type.getValue() : null);
      info.setHref(href != null ? href.getValue() : null);

      InputNode itemNode = node.getNext("img");
      while (itemNode != null) {
         Img img = new Img();
         img.setSrc(itemNode.getAttribute("src").getValue());
         img.setWidth(Integer.parseInt(itemNode.getAttribute("width").getValue()));
         img.setHeight(Integer.parseInt(itemNode.getAttribute("height").getValue()));
         imgItems.add(img);
         itemNode = node.getNext("img");
      }

      if (imgItems.size() == 0) {
         value = node.getValue();
      }

      info.setImg(imgItems);
      info.setValue(value);

      return info;
   }

   @Override
   public void write(OutputNode node, Info value) throws Exception {

   }
}