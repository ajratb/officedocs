/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715;

import javax.xml.bind.annotation.*;

/**
 *
 * @author ayrat
 */
@XmlRootElement
public class Student{
 
   String name;
   int age;
   int id;

   public String getName(){
      return name;
   }

   @XmlElement
   public void setName(String name){
      this.name = name;
   }

   public int getAge(){
      return age;
   }

   @XmlElement
   public void setAge(int age){
      this.age = age;
   }

   public int getId(){
      return id;
   }

   @XmlAttribute
   public void setId(int id){
      this.id = id;
   }
}
