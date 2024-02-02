package org.example;

import org.example.dao.ManufactureDAO;
import org.example.dao.PhoneDAO;
import org.example.pojo.Manufacture;
import org.example.pojo.Phone;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        ManufactureDAO manufactureDAO = ManufactureDAO.getInstance();
        PhoneDAO phoneDAO = PhoneDAO.getInstance();

        System.out.println("==================TEST==================");
        Manufacture apple = new Manufacture("1", "Apple", "US", 150, null);
        Phone iphoneX = new Phone("1", "Iphone X", 500, "Black", "US", 100, apple);
        Phone iphoneSE = new Phone("2", "Iphone SE", 600, "Pink", "VN", 500, apple);
        Manufacture oppo = new Manufacture("2", "Oppo", "Thailand", 90, null);
        Phone oppoF5 = new Phone("3", "Oppo F5", 9000, "Gray", "Thailand", 30, oppo);
        Phone oppoNeo = new Phone("4", "Oppo NEO", 800, "Red", "Thailand", 50, oppo);
        Manufacture samsung = new Manufacture("3", "Samsung", "US", 200, null);
        Phone samsungS8 = new Phone("5", "Samsung S8", 2000, "Purple", "US", 300, samsung);

        List<Phone> applePhones = new ArrayList<>();
        applePhones.add(iphoneX);
        applePhones.add(iphoneSE);
        apple.setPhoneList(applePhones);
        manufactureDAO.add(apple);

        List<Phone> oppoPhones = new ArrayList<>();
        oppoPhones.add(oppoF5);
        oppoPhones.add(oppoNeo);
        oppo.setPhoneList(oppoPhones);
        manufactureDAO.add(oppo);

        List<Phone> samsungPhones = new ArrayList<>();
        samsungPhones.add(samsungS8);
        samsung.setPhoneList(samsungPhones);
        manufactureDAO.add(samsung);

        System.out.println(manufactureDAO.getAll());
        System.out.println(phoneDAO.getAll());

        Manufacture changedManufacture = manufactureDAO.get("2");
        changedManufacture.setEmployee(120);
        manufactureDAO.update(changedManufacture);

        System.out.println(manufactureDAO.isAllManufacturesHaveMoreThan100Employees());
        System.out.println(manufactureDAO.getLastManufacturerInUS());
        System.out.println(manufactureDAO.totalEmployeesOfAllManufactures());

        System.out.println(phoneDAO.getAllSortByCountryAndDescPrice());

        Phone changedPhone = phoneDAO.get("1");
        changedPhone.setPrice(51000000);
        changedPhone.setColor("Pink");
        phoneDAO.update(changedPhone);

        System.out.println(phoneDAO.isExistPhonePriceAbove50Million());
        System.out.println(phoneDAO.getPhonePinkColorAndPriceOver15Million());
        System.out.println(phoneDAO.getPhoneHighestPrice());
        System.out.println("==================TEST==================");
    }
}
