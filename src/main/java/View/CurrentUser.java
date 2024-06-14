/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Person;

/**
 *
 * @author gerar
 */
public class CurrentUser {
   public static Person person = new Person();
   public void SetPerson(Person person)
   {
       this.person = person;
   }
   public Person GetPerson()
   {
       return this.person;
   }
   
}
